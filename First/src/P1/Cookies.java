package P1;

public enum  Cookies {    //“饼”枚举（奖品）
    yixiu("一秀",1),erju("二举",2),sanhon("三红",3),
    sijin("四进",4),duitang("对堂",5),zhuangyuan("状元",6),
    wuzidengke("五子登科",7),chajinhua("状元插金花",8),liubeihon("六杯红",9);

    int code;
    String name;
    Cookies(String _name,int _code)
    {
        code = _code;
        name = _name;
    }

    Cookies()
    {

    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getName(int index) {   //给定奖品序号，返回奖品名称。设置为静态方法
                                                //设置此项方法目的在于便于程序的维护，随时可以更改奖项种类和名称
                                                //不清楚这样使用是否合理
        switch (index){
            case 1:return Cookies.yixiu.getName();
            case 2:return Cookies.erju.getName();
            case 3:return Cookies.sanhon.getName();
            case 4:return Cookies.sijin.getName();
            case 5:return Cookies.duitang.getName();
            case 6:return Cookies.zhuangyuan.getName();
            default:return  " ";
        }
    }
}
