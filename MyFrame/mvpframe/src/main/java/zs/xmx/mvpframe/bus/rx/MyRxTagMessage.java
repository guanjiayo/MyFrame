package zs.xmx.mvpframe.bus.rx;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/12/14
 *     desc  :
 * </pre>
 */
final class MyRxTagMessage {

    Object mEvent;
    private String mTag;

    MyRxTagMessage(Object event, String tag) {
        mEvent = event;
        mTag = tag;
    }

    boolean isSameType(final Class eventType, final String tag) {
        return MyRxUtils.equals(getEventType(), eventType)
                && MyRxUtils.equals(this.mTag, tag);
    }

    Class getEventType() {
        return MyRxUtils.getClassFromObject(mEvent);
    }

    @Override
    public String toString() {
        return "event: " + mEvent + ", tag: " + mTag;
    }
}
