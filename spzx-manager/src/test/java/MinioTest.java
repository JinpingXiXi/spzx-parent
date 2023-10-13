import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Minio文件存储服务
 */
public class MinioTest {
    public static void main(String[] args) throws Exception {
        // 创建minio客户端对象，连接服务端
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("admin", "12345678")
                .build();

        //需要上传的文件
        InputStream inputStream = new FileInputStream("D:/1.jpg");

        // 上传文件
        minioClient.putObject(PutObjectArgs.builder()
                .bucket("spzx-bucket") //桶的名称
                .object("123.jpg") //文件保存在minio上的名称
                .stream(inputStream, inputStream.available(), -1) //文件流
                .build());
    }
}
