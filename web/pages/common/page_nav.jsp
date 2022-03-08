<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/2/7
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">
    <%--页数大于1才显示有上一页--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${ requestScope.page.url }&pageNo=1">首页</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1:如果总页码小于等于5的情况，页码的范围是:1-总页码--%>
        <%--设置当前页码不可点击 不是当前页码则可以跳转--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${requestScope.page.pageNo==i}">
                    【${i}】
                </c:if>
                <c:if test="${requestScope.page.pageNo!=i}">
                    <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--情况2:总页码大于5的情况。假设有10页--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--小情况1:当前页码为前3个:1,2,3,页码范围是1-5--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2:当前页码为最后3个,8,9,10,页码范围是:总页码-4-总页码--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--小情况3:4,5,6,7,页码范围是:当前页码减2-当前页码+2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--页码输出的结束--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            //跳到指定页码
            $("#searchPageBtn").click(function () {
                var pageNo=$("#pn_input").val();

                //判断输入的页码要大于1 小于总页数
                var pageTotal=${requestScope.page.pageTotal};

                // pageNo=Math.max(1,Math.min(pageNo,pageTotal));

                //JavaScript语言提供了一个location地址栏对象
                //它有一个属性叫href，它可以获取浏览器地址栏中的地址
                //href属性可读，可写
                location.href="${pageScope.basePath}${ requestScope.page.url }&pageNo="+pageNo;

            });

        });
    </script>

</div>