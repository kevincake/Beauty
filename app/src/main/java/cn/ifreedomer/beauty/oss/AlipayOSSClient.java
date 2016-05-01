package cn.ifreedomer.beauty.oss;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.ifreedomer.beauty.BeautyApplication;
import cn.ifreedomer.beauty.constants.Constants;
import cn.ifreedomer.beauty.util.FrescoUtils;
import cn.ifreedomer.beauty.util.LogUtil;

/**
 * @author:eavawu
 * @date: 5/1/16.
 * @todo: oss的客户端
 */
public class AlipayOSSClient {
    private static AlipayOSSClient instance = new AlipayOSSClient();
    private  static  OSSClient mOss;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static AlipayOSSClient getInstance() {
        return instance;
    }

    private AlipayOSSClient() {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(Constants.OSS_KEY,Constants.OSS_SECRET);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        mOss = new OSSClient(BeautyApplication.getInstance(), Constants.OSS_ENDPOINT , credentialProvider, conf);
    }

    /**
     * 创建者: eava
     * 创建时间: 2016/3/9 19:24
     * 功能说明: 上传
     */
    public  void uploadFileAsync(String uploadFileName, String uplodFilePath, final OSSCompletedCallback ossCompletedCallback) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(Constants.OSS_BUCKETNAME, uploadFileName, uplodFilePath);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                LogUtil.info("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
        OSSAsyncTask task = mOss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(final PutObjectRequest putObjectRequest, final PutObjectResult putObjectResult) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ossCompletedCallback.onSuccess( putObjectRequest,  putObjectResult);
                    }
                });
            }

            @Override
            public void onFailure(final PutObjectRequest putObjectRequest, final ClientException e, final ServiceException e1) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ossCompletedCallback.onFailure(putObjectRequest,  e,  e1);
                    }
                });
            }
        });
    }

    /**
     * 创建者: eava
     * 创建时间: 2016/3/9 19:24
     * 功能说明: 下载
     */
    public  void downloadFileAsync(String downLoadFileName, OSSCompletedCallback ossCompletedCallback) {
        GetObjectRequest get = new GetObjectRequest(Constants.OSS_BUCKETNAME, downLoadFileName);
        OSSAsyncTask task = mOss.asyncGetObject(get, ossCompletedCallback
        );
    }

    public  String getUrl(String fileObj) {
        String url = "";
        try {
            url = mOss.presignPublicObjectURL(Constants.OSS_BUCKETNAME, fileObj);
            LogUtil.info("signContrainedURL", "get url: " + url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }


    public  void uploadAndSetImageView(final String photoName, final String localPath, final SimpleDraweeView simpleDraweeView){
        AlipayOSSClient.getInstance().uploadFileAsync(photoName, localPath, new OSSCompletedCallback() {
            @Override
            public void onSuccess(OSSRequest ossRequest, OSSResult ossResult) {
                String url = AlipayOSSClient.getInstance().getUrl(photoName);
                FrescoUtils.setImageUrl(url, simpleDraweeView);
            }

            @Override
            public void onFailure(OSSRequest ossRequest, ClientException e, ServiceException e1) {

            }
        });
    }


}
