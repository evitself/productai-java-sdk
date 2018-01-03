package cn.productai.api.examples.color;

import cn.productai.api.core.IWebClient;
import cn.productai.api.core.enums.*;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.examples.IExample;
import cn.productai.api.pai.entity.color.*;

import java.io.File;

public class ColorAnalysisByFileExample implements IExample {

    @Override
    public void run(IWebClient client) {

        System.out.println("Demo - 色彩标注服务");

        ColorClassifyByImageFileRequest request = new ColorClassifyByImageFileRequest("0-0-1-1");
        request.setSubType(SubType.EveryThing);
        request.setGranularity(Granularity.Major);
        request.setReturnType(ColorReturnType.CNCS);
        request.setImageFile(new File(this.getClass().getResource("/").getPath() + "images/f10.jpg"));
        request.setLanguage(LanguageType.Chinese);

        // option paras
        request.getOptions().put("ret_img_tags", "1");

        try {
            ColorAnalysisResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            System.out.println(response.getResponseJsonString());

            System.out.println("==============================Result==============================");
        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                    e.getErrorCode(),
                    e.getErrorMessage()));
            e.printStackTrace();

        } catch (ClientException e) {
            System.out.println(String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                    e.getErrorCode(),
                    e.getErrorMessage(),
                    e.getRequestId()));
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }
}
