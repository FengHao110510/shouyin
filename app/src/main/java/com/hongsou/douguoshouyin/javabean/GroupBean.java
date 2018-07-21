package com.hongsou.douguoshouyin.javabean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/21 0021
 * 描述：
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class GroupBean {

    /**
     * code : 1468
     * msg : veniam nostrud
     * extInfo : {}
     * data : [{"groupName":"eu","groupNumber":"sint esse","groupCount":"magna sunt dolor do","productList":[{"foodFullName":"ex eu veniam culpa eiusmod","foodProductsCount":8978270,"standard":"enim reprehenderit amet sint","minGroup":"labore tempor","foodProductsNumber":"tempor ea officia sunt"},{"foodFullName":"Lorem aliqua aliquip in","foodProductsCount":-20357097,"standard":"mollit officia cillum ullamco","minGroup":"ad anim velit consequat","foodProductsNumber":"labore ipsum occaecat in"},{"foodFullName":"culpa consequat ut","foodProductsCount":-86235357,"standard":"veniam eiusmod dolor consectetur","minGroup":"ut cillum culpa","foodProductsNumber":"nulla et eu aliqua"},{"foodFullName":"magna occaecat sunt est","foodProductsCount":-59262818,"standard":"sunt minim e","minGroup":"adipisicing sunt dolore dolor ullamco","foodProductsNumber":"ex Excepteur amet"},{"foodFullName":"proident ex","foodProductsCount":-15384251,"standard":"et elit in in","minGroup":"commodo aute","foodProductsNumber":"sunt"}]},{"groupName":"Lorem pariatur labore quis","groupNumber":"exercitation consequat","groupCount":"dolore Ut occaecat","productList":[{"foodFullName":"aliqua fugiat labore","foodProductsCount":8274364,"standard":"magna reprehenderit","minGroup":"pariatur","foodProductsNumber":"laborum ipsum dolor aliqua"}]},{"groupName":"ut","groupNumber":"sit ut elit Ut","groupCount":"Ut in ","productList":[{"foodFullName":"laboris velit ad","foodProductsCount":-18444724,"standard":"aute","minGroup":"ea fugiat do Duis","foodProductsNumber":"irure voluptate velit adipis"},{"foodFullName":"in ipsum ea incididunt fugiat","foodProductsCount":48009825,"standard":"deserunt sit do","minGroup":"aliqua deserunt aute enim","foodProductsNumber":"Ut exercitation sit"},{"foodFullName":"in eu proident","foodProductsCount":87253424,"standard":"irure proident","minGroup":"adipisicing cupidatat","foodProductsNumber":"enim culpa mollit esse"}]}]
     * success : false
     */

    private int code;
    private String msg;
    private ExtInfoBean extInfo;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ExtInfoBean getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(ExtInfoBean extInfo) {
        this.extInfo = extInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ExtInfoBean {
    }

    public static class DataBean {
        /**
         * groupName : eu
         * groupNumber : sint esse
         * groupCount : magna sunt dolor do
         * productList : [{"foodFullName":"ex eu veniam culpa eiusmod","foodProductsCount":8978270,"standard":"enim reprehenderit amet sint","minGroup":"labore tempor","foodProductsNumber":"tempor ea officia sunt"},{"foodFullName":"Lorem aliqua aliquip in","foodProductsCount":-20357097,"standard":"mollit officia cillum ullamco","minGroup":"ad anim velit consequat","foodProductsNumber":"labore ipsum occaecat in"},{"foodFullName":"culpa consequat ut","foodProductsCount":-86235357,"standard":"veniam eiusmod dolor consectetur","minGroup":"ut cillum culpa","foodProductsNumber":"nulla et eu aliqua"},{"foodFullName":"magna occaecat sunt est","foodProductsCount":-59262818,"standard":"sunt minim e","minGroup":"adipisicing sunt dolore dolor ullamco","foodProductsNumber":"ex Excepteur amet"},{"foodFullName":"proident ex","foodProductsCount":-15384251,"standard":"et elit in in","minGroup":"commodo aute","foodProductsNumber":"sunt"}]
         */

        private String groupName;
        private String groupNumber;
        private String groupCount;
        private List<ProductListBean> productList;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupNumber() {
            return groupNumber;
        }

        public void setGroupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
        }

        public String getGroupCount() {
            return groupCount;
        }

        public void setGroupCount(String groupCount) {
            this.groupCount = groupCount;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class ProductListBean {
            /**
             * foodFullName : ex eu veniam culpa eiusmod
             * foodProductsCount : 8978270
             * standard : enim reprehenderit amet sint
             * minGroup : labore tempor
             * foodProductsNumber : tempor ea officia sunt
             */

            private String foodFullName;
            private int foodProductsCount;
            private String standard;
            private String minGroup;
            private String foodProductsNumber;

            public String getFoodFullName() {
                return foodFullName;
            }

            public void setFoodFullName(String foodFullName) {
                this.foodFullName = foodFullName;
            }

            public int getFoodProductsCount() {
                return foodProductsCount;
            }

            public void setFoodProductsCount(int foodProductsCount) {
                this.foodProductsCount = foodProductsCount;
            }

            public String getStandard() {
                return standard;
            }

            public void setStandard(String standard) {
                this.standard = standard;
            }

            public String getMinGroup() {
                return minGroup;
            }

            public void setMinGroup(String minGroup) {
                this.minGroup = minGroup;
            }

            public String getFoodProductsNumber() {
                return foodProductsNumber;
            }

            public void setFoodProductsNumber(String foodProductsNumber) {
                this.foodProductsNumber = foodProductsNumber;
            }
        }
    }
}
