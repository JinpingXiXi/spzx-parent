import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploadTest {
    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //BUILD THE CLIENT
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("admin", "12345678")
                .build();

        //check if the bucket already in minio
        boolean isSPZXBucketAlreadyExists = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket("spzx-bucket").build()
        );

        //if not found, create a new one
        if (!isSPZXBucketAlreadyExists)
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("spzx-bucket").build());
        else
            System.out.println("Bucket 'spzx-bucket' already exists");

        FileInputStream fileInputStream =
                new FileInputStream("D://docker.png");

        PutObjectArgs minioObjectBuild =
                PutObjectArgs.builder()
                        .bucket("spzx-bucket")
                        .stream(fileInputStream, fileInputStream.available(), -1)
                        .object("docker.png")
                        .build();
        minioClient.putObject(minioObjectBuild);

        String fileurl = "http://127.0.0.1:9000/spzx-bucket/docker.png";
        System.out.println(fileurl);
    }
    }
