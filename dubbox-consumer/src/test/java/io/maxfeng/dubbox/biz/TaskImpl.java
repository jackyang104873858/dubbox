package io.maxfeng.dubbox.biz;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class TaskImpl implements Task {
    @Override
    public void setData(String data) {
        System.out.println(data+ " Data is saved");
    }
    @Override
    public int getCalData(int x) {
        return x*10;
    }
}
