/**
* each是一个集合迭代函数，它接受一个函数作为参数和一组可选的参数
* 这个迭代函数依次将集合的每一个元素和可选参数用函数进行计算，并将计算得的结果集返回
{%example
<script>
     var a = [1,2,3,4].each(function(x){return x > 2 ? x : null});
     var b = [1,2,3,4].each(function(x){return x < 0 ? x : null});
     alert(a);
     alert(b);
</script>
%}
* @param {Function} fn 进行迭代判定的函数
* @param more ... 零个或多个可选的用户自定义参数
* @returns {Array} 结果集，如果没有结果，返回空集
*/
Array.prototype.iterator = function(fn){
    fn = fn || Function.K;
     var a = [];
     var args = Array.prototype.slice.call(arguments, 1);
     for(var i = 0; i < this.length; i++){
         var res = fn.apply(this,[this[i],i].concat(args));
         if(res != null) a.push(res);
     }
     return a;
};

/**
* 得到一个数组不重复的元素集合<br/>
* 唯一化一个数组
* @returns {Array} 由不重复元素构成的数组
*/
Array.prototype.uniquelize = function(){
     var ra = new Array();
     for(var i = 0; i < this.length; i ++){
         if(!ra.contains(this[i])){
            ra.push(this[i]);
         }
     }
     return ra;
};
/**
 * 判断数组中是否包含某个对象，
 * 如果包含，则 返回 true; 否则 返回 false;
 */
Array.prototype.contains = function (elem) {
   for (var i = 0; i < this.length; i++) {
            if (this[i] == elem) {
                    return true;
            }
    }
    return false;
}
/**
 * 查找数组中对象所在的索引位置；
 */
Array.prototype.indexOf = function(o){   
    for(var i = 0 ;  i<this.length;i++){
           if(this[i] == o){
                return i;
           }
    }
    return -1;
};
/**
 * 移除数组中的对象；
 * @param {} o
 * @return {}
 */
Array.prototype.deleteObj = function(o){
   var index = this.indexOf(o);
   if(index != -1){
         this.splice(index,1)
     }
   return this;
}

/**
* 求两个集合的补集
{%example
<script>
     var a = [1,2,3,4];
     var b = [3,4,5,6];
     alert(Array.complement(a,b));
</script>
%}
* @param {Array} a 集合A
* @param {Array} b 集合B
* @returns {Array} 两个集合的补集
*/
Array.complement = function(a, b){
     return Array.minus(Array.union(a, b),Array.intersect(a, b));
};

/**
* 求两个集合的交集
{%example
<script>
     var a = [1,2,3,4];
     var b = [3,4,5,6];
     alert(Array.intersect(a,b));
</script>
%}
* @param {Array} a 集合A
* @param {Array} b 集合B
* @returns {Array} 两个集合的交集
*/
Array.intersect = function(a, b){
     return a.uniquelize().iterator(function(o){return b.contains(o) ? o : null});
};

/**
* 求两个集合的差集
{%example
<script>
     var a = [1,2,3,4];
     var b = [3,4,5,6];
     alert(Array.minus(a,b));
</script>
%}
* @param {Array} a 集合A
* @param {Array} b 集合B
* @returns {Array} 两个集合的差集
*/
Array.minus = function(a, b){
     return a.uniquelize().iterator(function(o){return b.contains(o) ? null : o});
};

/**
* 求两个集合的并集
{%example
<script>
     var a = [1,2,3,4];
     var b = [3,4,5,6];
     alert(Array.union(a,b));
</script>
%}
* @param {Array} a 集合A
* @param {Array} b 集合B
* @returns {Array} 两个集合的并集
*/
Array.union = function(a, b){
     return a.concat(b).uniquelize();
};

