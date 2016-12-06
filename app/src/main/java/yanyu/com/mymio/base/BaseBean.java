package yanyu.com.mymio.base;

/**
 * Created by zq on 2016/12/1 0001.
 */

public class BaseBean {
    public boolean result;
    public Object message;
    public Object data;
    public String nonce_str;
    public double time_spend;

    @Override
    public String toString() {
        return "BaseBean{" +
                "result=" + result +
                ", message=" + message +
                ", data=" + data +
                ", nonce_str='" + nonce_str + '\'' +
                ", time_spend=" + time_spend +
                '}';
    }
}
