package transaction.service.weixin.encryption;
import java.util.ArrayList;

/**
 * Created by zhengjianhui on 17/5/9.
 */
public class ByteGroup {
    ArrayList<Byte> byteContainer = new ArrayList<Byte>();

    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
        return this;
    }

    public int size() {
        return byteContainer.size();
    }
}
