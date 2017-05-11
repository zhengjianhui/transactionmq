package tmp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import transaction.service.weixin.encryption.WXBizMsgCrypt;

/**
 * Created by zhengjianhui on 17/5/9.
 */
public class Test {


    public static void main(String[] args) throws Exception {

        //
        // 第三方回复公众平台
        //

        // 需要加密的明文
        String encodingAesKey = "GjD5MpWfrw36OtJd4Kgt0pFU5FbFrcqMLm2e5ni6lwz";
        String token = "oumind-UJL-2015";
        String appId = "wx219c80e3b8cb1def";

        String timestamp = "1494334534";
        String nonce = "1526080254";
        String replyMsg = "<xml>\n" +
                "    <AppId><![CDATA[wx219c80e3b8cb1def]]></AppId>\n" +
                "    <Encrypt><![CDATA[edvTm8dTo9m+4LbWgvMBrZ3ZmOvCMnysLEISNcgbq99B4dFh1Jo30qUoS2lIujO1MVkdlHwx55/9sgA3OirMDE+UwkgGG5WdMXiWwG9ZM5KKiWlw0pQWj3+R2P1AwYE0LpDEqP4Jjw4c9BY1sMQOnK0+ynon0xSTk2SEjrUWG8Kw7xlqtPUamtfejlV4lZIN0toxF16lg8SBf0+e7j1c73noakSLgii6d7Yex910xrljrQ0tRYLBrIjPZ0nh8VoV3dqfNyDsM5GEj17hTG9B4n72rwTs1UJEooARBxeQkhN26eTYAiTGdquJL+PT7O+RXWx+tFx8wAINyTbaL7p4/Ca6xAv+P7u5KmJyWnlCcjilTg0rab8t4VdcUXwFtlasJP3vCpNAzP8vhR11eUCZbDJ/fsURaEZlVYyJlZVIltpqeSx6l83JJKs70rkSPO/SqvK+BnVgdAhCC8Vvb7VV/g==]]></Encrypt>\n" +
                "</xml>\n";

        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
        String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
        System.out.println("加密后: " + mingwen);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(mingwen);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodelist1 = root.getElementsByTagName("Encrypt");
//		NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

        String encrypt = nodelist1.item(0).getTextContent();
//		String msgSignature = nodelist2.item(0).getTextContent();

        String format = "<xml>\n" +
                "    <AppId><![CDATA[wx219c80e3b8cb1def]]></AppId>\n" +
                "    <Encrypt><![CDATA[edvTm8dTo9m+4LbWgvMBrZ3ZmOvCMnysLEISNcgbq99B4dFh1Jo30qUoS2lIujO1MVkdlHwx55/9sgA3OirMDE+UwkgGG5WdMXiWwG9ZM5KKiWlw0pQWj3+R2P1AwYE0LpDEqP4Jjw4c9BY1sMQOnK0+ynon0xSTk2SEjrUWG8Kw7xlqtPUamtfejlV4lZIN0toxF16lg8SBf0+e7j1c73noakSLgii6d7Yex910xrljrQ0tRYLBrIjPZ0nh8VoV3dqfNyDsM5GEj17hTG9B4n72rwTs1UJEooARBxeQkhN26eTYAiTGdquJL+PT7O+RXWx+tFx8wAINyTbaL7p4/Ca6xAv+P7u5KmJyWnlCcjilTg0rab8t4VdcUXwFtlasJP3vCpNAzP8vhR11eUCZbDJ/fsURaEZlVYyJlZVIltpqeSx6l83JJKs70rkSPO/SqvK+BnVgdAhCC8Vvb7VV/g==]]></Encrypt>\n" +
                "</xml>\n";
        String fromXML = String.format(format, encrypt);

        //
        // 公众平台发送消息给第三方，第三方处理
        //

        // 第三方收到公众号平台发送的消息
        String result2 = pc.decryptMsg("814de3a3ba17102924e8e87bc89b740a5fca5f28", timestamp, nonce, fromXML);
        System.out.println("解密后明文: " + result2);

        //pc.verifyUrl(null, null, null, null);
    }
}
