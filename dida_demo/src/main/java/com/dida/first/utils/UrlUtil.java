package com.dida.first.utils;

import android.net.Uri;

public class UrlUtil {
    public  enum InterfaceName{
        I_MARKET_LIST,I_MARKET_DETAIL,I_MARKET_ADD_CANCLE_COLLECT,I_PINGOU_LIST,I_PINGOU_ADD_CANCLE_COLLECT,I_PINGOU_DETAIL,I_SHOW_LIST,I_SHOW_EDIT,I_REGISTER_SMS,I_PINGOU_MORE_COMMENT,
        I_ADDRESS_LIST,I_STORE_LIST,I_STORE_DETAIL,I_STORE_COLLECT,I_EDIT_USERINFO,I_UPDATE_ADDRESS,I_REGISTER_CODE,I_REGISTER,I_MINE_PINGOU,I_FAV_PINGOU,I_LOGIN
    }

//    public final static String HOST = "http://121.40.28.206";//主机名
    public final static String IMG = "http://img.aamai.cn";//图片域名
    public final static String HOST = "http://192.168.1.178:8080";//主机名
    /*==============================================集市==============================================*/
    public final static String MARKET_LIST = "/commodity/queryAllCommodity.do";//集市列表
    public final static String MARKET_DETAIL = "/commodity/queryDetailCommodity.do";//集市详情
    public final static String MARKET_IF_COLLECT = "/collection/isCollected.do";//集市是否加入过代购单
    public final static String MARKET_SEARCH = "/commodity/searchCommodityByName.do";//集市商品搜索
    public final static String MARKET_ADD_CANCLE_COLLECT = "/collection/addOrDelProduct.do";//集市添加删除收藏
    /*==============================================拼购==============================================*/
    public final static String PINGOU_LIST = "/service/queryAllTask.do";//拼购列表
    public final static String PINGOU_DETAIL = "/service/detailSerivce.do";//拼购详情
    public final static String PINGOU_SEARCH = "/service/queryAllTask.do";//拼购搜索
    public final static String PINGOU_ADD_CANCLE_COLLECT = "/collection/addOrDelCollection.do";//拼购添加删除收藏
    public final static String PINGOU_MORE_COMMENT = "/service/queryAllReply.do";//拼购更多评论
    /*==============================================晒单==============================================*/
    public final static String SHOW_LIST = "/prepayorder/getPrepayOrders.do";//晒单列表
    public final static String SHOW_EDIT = "/prepayorder/selectPrepayOrder.do";//晒单编辑
    public final static String SHOW_PUBLISH = "/group/saveGroup.do";//晒单发布
    public final static String REGISTER_SMS = "/collection/deleteProduct.do";
    /*==============================================我的==============================================*/
    public final static String ADDRESS_LIST = "/information/queryMyDeliveryAddress.do";
    public final static String STORE_LIST = "/collection/getShopCollectionList.do";
    public final static String STORE_DETAIL = "/shop/getShopIntroduce.do";
    public final static String STORE_COLLECT = "/collection/addOrDelShop.do";
    public final static String EDIT_USERINFO = "/usercenter/updateUserInfomation.do";
    public final static String UPDATE_ADDRESS = "/information/updateDeliveryAddress.do";
    public final static String MINE_PINGOU = "/service/myGroup.do";
    /*==============================================注册登录==============================================*/
    public final static String REGISTER_CODE = "/user/getMobileCode.do";
    public final static String REGISTER = "/user/appRegist.do";
    public final static String FAV_PINGOU = "/collection/getGroupCollection.do";
    public final static String LOGIN = "/user/appLogin.do";

    /**
     * 获取接口地址
     * @param interfaceName
     * @return
     */
    public static String getIUrl(InterfaceName interfaceName){
        String url=HOST;
        switch (interfaceName){
            //集市列表
            case I_MARKET_LIST:
                url+=MARKET_LIST;
                break;
            //集市详情
            case I_MARKET_DETAIL:
                url+=MARKET_DETAIL;
                break;
            //拼购列表
            case I_PINGOU_LIST:
                url+=PINGOU_LIST;
                break;
            //拼购详情
            case I_PINGOU_DETAIL:
                url+=PINGOU_DETAIL;
                break;
            //拼购添加删除收藏
            case I_PINGOU_ADD_CANCLE_COLLECT:
                url+=PINGOU_ADD_CANCLE_COLLECT;
                break;
            //拼购更多评论
            case I_PINGOU_MORE_COMMENT:
                url+=PINGOU_MORE_COMMENT;
                break;
            //集市添加删除收藏
            case I_MARKET_ADD_CANCLE_COLLECT:
                url+=MARKET_ADD_CANCLE_COLLECT;
                break;
            //晒单列表
            case I_SHOW_LIST:
                url+=SHOW_LIST;
                break;
            //晒单编辑
            case I_SHOW_EDIT:
                url += SHOW_EDIT;
                break;
            //地址列表
            case I_ADDRESS_LIST:
                url += ADDRESS_LIST;
                break;
            //收藏店铺列表
            case I_STORE_LIST:
                url += STORE_LIST;
                break;
            //店铺详情
            case I_STORE_DETAIL:
                url += STORE_DETAIL;
                break;
            //店铺添加/删除收藏
            case I_STORE_COLLECT:
                url += STORE_COLLECT;
                break;
            //修改个人信息
            case I_EDIT_USERINFO:
                url += EDIT_USERINFO;
                break;
            //修改地址
            case I_UPDATE_ADDRESS:
                url += UPDATE_ADDRESS;
                break;
            //获取验证码
            case I_REGISTER_CODE:
                url += REGISTER_CODE;
                break;
            //注册
            case I_REGISTER:
                url += REGISTER;
                break;
            //我的拼购
            case I_MINE_PINGOU:
                url += MINE_PINGOU;
                break;
            //收藏的拼购
            case I_FAV_PINGOU:
                url += FAV_PINGOU;
                break;
            //登录
            case I_LOGIN:
                url += LOGIN;
                break;
            default:
                break;
        }
        return url;

    }

    public static String getImgUrl(String url) {
        if (!url.startsWith("http")) {
            url = IMG+url;
        }
        return url;
    }
    public static String getUrl(String url) {
        if (!url.startsWith("http")) {
            url = HOST+url;
        }
        return url;
    } public static Uri getUri(String url) {
        if (!url.startsWith("http")) {
            url = IMG+url;
        }
        return Uri.parse(url);
    }
    public static  Uri getResourceUri(int resId,String packageName)
    {
        return Uri.parse("android.resource://"+AppInfoUtil.getPackageName()+"/"+resId);
    }

}
