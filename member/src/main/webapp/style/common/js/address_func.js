/*地区类*/
function place(AreaCode, Name) {
    this.AreaCode = AreaCode;//地区编码
    this.Name = Name;//地名

    /*地区类的PlaceType方法，返回值：String类型，表示地区类别，"p"代表省/直辖市、特别行政区，"c"代表市，"d"代表区/县。*/
    place.prototype.PlaceType = function () {
        var ac = parseInt(this.AreaCode);
        if (ac % 100 != 0) {
            return "d";
        } else if (ac % 10000 != 0) {
            return "c";
        } else {
            return "p";
        }
    }
    /*返回地点所属省编码*/
    place.prototype.ProvinceCode = function () {
        //整除10000得到省级编码（前2位数字）
        var ac = parseInt(this.AreaCode);
        return Math.floor(ac / 10000);
    }
    /*返回地点所属市编码*/
    place.prototype.CityCode = function () {
        //整除100得到市级编码（前4位数字）
        var ac = parseInt(this.AreaCode);
        return Math.floor(ac / 100);
    }
}

var provinces = new Array();//省数组
var cities = new Array();//市数组
var districts = new Array();//区数组
var documentProvinceName = "province";
var documentCityName = "city";
var documentDistrictName = "district";
var addressDefultValue = "000000";

/*initAddressList()这个函数初始化上面这三个数组，还有省下拉列表*/
function initAddressList(provinceId, cityId, districtId) {
    /* 设置控件名称 */
    documentProvinceName = provinceId;
    documentCityName = cityId;
    documentDistrictName = districtId;

    //遍历placesMap这个Json对象,根据key：value对创建place对象，并根据地区类型分类
    for (var key in placesMap) {
        var pl = new place(key, placesMap[key]);
        var ty = pl.PlaceType();
        if (ty == "p") {
            provinces.push(pl);
        }
        if (ty == "c") {
            cities.push(pl);
        }
        if (ty == "d") {
            districts.push(pl);
        }
    }
    //初始化省下拉选择列表
    for (var i = 0; i < provinces.length; i++) {
        var op = document.createElement("option");
        op.text = provinces[i].Name;
        op.value = provinces[i].ProvinceCode();
        document.getElementById(documentProvinceName).appendChild(op);
    }
}

function getPlaceCode(documentId, placeName) {
    if (documentId == documentProvinceName) {
        for (var i = 0; i < provinces.length; i++) {
            if (provinces[i].Name == placeName) {
                return provinces[i].ProvinceCode();
            }
        }
    }else if (documentId == documentCityName){
        for (var i=0; i<cities.length; i++){
            if (cities[i].Name == placeName) {
                return cities[i].CityCode();
            }
        }
    }else {
        for (var i=0; i<districts.length; i++){
            if (districts[i].Name == placeName) {
                return districts[i].AreaCode;
            }
        }
    }
}

/*下拉列表选项改变时执行此函数*/
function changeSelectAddress(element) {
    var id = element.getAttribute("id");
    console.log(id);
    /*省下拉列表改变时更新市下拉列表和区下拉列表*/
    if (id == documentProvinceName && element.value != addressDefultValue) {
        document.getElementById(documentCityName).options.length = 1;//清除市下拉列表旧选项
        var pCode = parseInt(element.value);//获取省下拉列表被选取项的省编码
        console.log(element.value +","+element[element.selectedIndex].text );
        /*根据省编码更新市下拉列表*/
        if (pCode != 0) {
            for (var i = 0; i < cities.length; i++) {
                if (cities[i].ProvinceCode() == pCode) {
                    var op = document.createElement("option");
                    op.text = cities[i].Name;
                    op.value = cities[i].CityCode();
                    document.getElementById(documentCityName).appendChild(op);
                }
            }
        }
        document.getElementById(documentDistrictName).options.length = 1;//清除区下拉列表旧选项
        var cCode = parseInt(document.getElementById(documentCityName).value);//获取市下拉列表被选中项的市编码
        /*根据市编码更新区下拉列表*/
        if (cCode != 0) {
            for (var i = 0; i < districts.length; i++) {
                if (districts[i].CityCode() == cCode) {
                    var op = document.createElement("option");
                    op.text = districts[i].Name;
                    op.value = districts[i].AreaCode;
                    document.getElementById(documentDistrictName).appendChild(op);
                }
            }
        }
    }
    /*市下拉列表改变时更新区下拉列表的选项*/
    if (id == documentCityName && element.value != addressDefultValue) {
        document.getElementById(documentDistrictName).options.length = 1;//清除区下拉列表旧选项
        var cCode = parseInt(element.value);//获取市下拉列表被选中项的市编码
        /*根据市编码更新区下拉列表*/
        console.log(cCode +"," );
        for (var i = 0; i < districts.length; i++) {
            if (districts[i].CityCode() == cCode) {
                var op = document.createElement("option");
                op.text = districts[i].Name;
                op.value = districts[i].AreaCode;
                document.getElementById(documentDistrictName).appendChild(op);
            }
        }

    }
}