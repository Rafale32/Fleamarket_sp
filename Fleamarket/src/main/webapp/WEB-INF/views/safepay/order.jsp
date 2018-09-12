<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 20180818 재헌 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 다음 주소 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- 필요한 메서드 담아둠-->
<script src="../resources/safepay/js/orderJS.js" ></script>

<!-- css -->
<link rel="stylesheet" href="../resources/safepay/css/orderCSS.css" type="text/css">

<!-- 부트스트랩 -->
<link href="../resources/boot/bootstrap/css/bootstrap.min.css" rel="stylesheet" xtype="text/css" />
<script src="../resources/boot/bootstrap/js/bootstrap.min.js"></script>

<title>주문/결제</title>
</head>
<body>

  <!-- 	주문결제 -->
  <div class="container">
  <form action="order" method="post" onsubmit="return payCheck()">
     <!--  구매자 이메일 주소 -->
     <c:set var="email" value="${member.member_email}"></c:set>
     구매자 메일<input type="text" name="email" value="${email }">
     <c:set var="member_no" value="${member.member_no}"></c:set>
     구매자회원번호<input type="text" name="member_no" value="${member_no }">
     <!--  상품번호 -->
     <c:set var="item_no" value="${bean.itemDTO.item_No}"></c:set>
     상품번호<input type="text"  name="item_no" value="${item_no}"> 
     <!--  마지막 주문번호를 불러와서 +1 시켜서 현재 페이지의 주문번호를 정한다. -->
     <c:set var="oorder_no" value="${bean.oorderDTO.oorder_no+1}"></c:set>
     주문번호<input type="text" name="oorder_no" value="${oorder_no}">
     
      <table class="order_table" id="delivery_table">
      <tr>
      <td colspan="3">
        <h1>주문/결제</h1>
      </td>
      </tr>
        <tr>
          <td>수령인</td>
          <td colspan="2">
            <input type="text" name="delivery_name" value="${member.member_name}">
          </td>
        </tr>
        <tr>
          <td>연락처</td>
          <td colspan="2">
            <input type="text" name="delivery_ph" value="${member.member_phone}">
          </td>
        </tr>
        <tr>
          <td rowspan="2">배송지</td>
          <td>
            <input type="text" name="delivery_address" id="delivery_address"
                   value="${member.member_address}" placeholder="주소" size="50">
          </td>
          <td class="right_text">
            <input type="button" class="btn" value="주소검색" onclick="search_address()">
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <input type="text" name="delivery_address2" id="delivery_address2"
                   value="${member.member_address2}" placeholder="상세주소" size="50">
          </td>
        </tr>
        <tr>
          <td colspan="3"> 배송요청사항 </td>
        </tr>
        <tr>
          <td colspan="3">
            <input type="text" name="delivery_contents" placeholder="경비실에 맡겨주세요" size="80">
          </td>
        </tr>
      </table>
  </div>      

  <!-- 주문상품   -->
  <div>
    <c:set var="price" value="${bean.itemDTO.price}"></c:set>
    <hr>
    <table class="order_table" id="item_table">
    <tr>
      <td colspan="3">
        <h2>주문상품</h2>
      </td>
    </tr>
    <tr>
     <td colspan="3">${bean.itemDTO.store_name}</td>
    </tr>
    <tr>
      <td rowspan="2">
        <c:set var="img" value="${bean.itemImgDTO_jh.thum_img}"></c:set>
        <img src="../productimg/${img}" width="200px" height="150px">
      </td>
      <td>상품명</td>
      <td class="right_text">${bean.itemDTO.title}</td>
    </tr>
    <tr>
      <td>가격</td>
      <td class="right_text"><fmt:formatNumber value="${price}" />원</td>
    </tr>
  </table>
  </div>

  <!-- 포인트 -->
  <div>
    <hr>
    <table class="order_table" id="point_table">
    <tr>
      <td colspan="3"><h2>포인트</h2></td>
    </tr>
    <tr>
      <td colspan="3">100원 단위로 사용가능합니다.</td>
    </tr>
      <tr>
        <td>잔여</td>
        <c:set var="havePoint" value="${member.member_point}"></c:set>
        <td id="havePoint" colspan="2"><fmt:formatNumber value="${havePoint}" />원</td>

      </tr>
      <tr>
        <td>사용</td>
        <td><c:set var="use" value="0"></c:set>
        <input type="number"
               id="usePoint"
               value="${use}"
               step = "100"
               min = "0"
               max = "${havePoint}"
               onchange="changePoint()">
        </td>
          <td class="right_text">
            <!--  버튼 클릭시 포인트 보이는 부분 변경 20180818 재헌 -->
            <input type="button" id="allUseButton" class="btn" value="전액사용"
                   onclick="allPoint(${havePoint})">
          </td>
      </tr>
    </table>

  </div>

  <!-- 결제금액 -->
  <div>
    <hr>
    <table class="order_table" id="pay_table">
    <tr>
      <td colspan="2"><h2>결제금액</h2></td>
    </tr>
      <tr>
        <td>상품금액</td>
        <td  class="right_text" id="price"><fmt:formatNumber value="${price}" />원</td>
      </tr>
      <tr>
        <td>배송비</td>
        <td id="delivery_fee"  class="right_text">
          <!--  배송 유무 확인 -->
          <c:choose>
            <c:when test="${bean.itemDTO.delivery_Fee == '1'}">
              <c:set var="deli_fee" value="2500"></c:set>
              <fmt:formatNumber value="${deli_fee}" />원
            </c:when>
            <c:otherwise>
              <c:set var="deli_fee" value="0"></c:set>
               무료배송
            </c:otherwise>
          </c:choose>

        </td>
      </tr>
      <!--  사용포인트 -->
      <tr>
        <td>포인트</td>
        <c:set var="finalPoint" value="${use}"></c:set>
        <td id="finalPoint"  class="right_text">
          <fmt:formatNumber value="${finalPoint}" />원
        </td>
      </tr>
      <!--  수수료 -->
      <tr>
        <td>수수료</td>
        <td id="fee"  class="right_text"><fmt:formatNumber value="${price*0.03+1000}" />원</td>
      </tr>
      <!--  총 결제 금액   -->
      <tr>
        <td><b>최종결제금액</b></td>
        <c:set var="total" value="${price+(price*0.03+1000)+deli_fee}"></c:set>
        <td id="total"  class="right_text">
          <b><fmt:formatNumber value="${total}" />원</b>
        </td>
      </tr>
    </table>
    <!--     넘긴다 -->
    <!--     소모 포인트 -->
    <fmt:parseNumber var="finalPoint2" value="${finalPoint}" integerOnly="true" />
    소모 포인트<input type="text" id="finalPoint2" name="pay_usepoint" value="${finalPoint2}">
<%--     <fmt:parseNumber var="finalPoint2" value="${finalPoint}" integerOnly="true" /> --%>
<%--     소모 포인트<input type="text" id="finalPoint2" name="finalPoint2" value="${finalPoint2}"> --%>
    <!--     총 금액 -->
    <fmt:parseNumber var="total2" value="${total}" integerOnly="true" />
    총 결제금액<input type="text" id="total2" name="pay_price" value="${total2}">
<%--     <fmt:parseNumber var="total2" value="${total}" integerOnly="true" /> --%>
<%--     총 결제금액<input type="text" id="total2" name="total2" value="${total2}"> --%>
    <!--     계산된 포인트 -->
    <fmt:parseNumber var="point" value="${startPoint-finalPoint+(total*0.001) }" integerOnly="true" />
    계산된 포인트<input type="text" id="point" name="point" value="${point}">
    
  </div>
  <hr>
  <!-- 결제 하단 -->
  <table id="pay_table" class="order_table">
  <tr >
    <td class="right_text"><input id="chk" type="checkbox"> 주문 상품정보 및 서비스 이용약관에 동의합니다.</td>
  </tr>
  <tr>
    <td class="right_text"><input type="submit" class="btn" value="결제하기" /></td>
  </tr>
  </table>
  </form>
  <br><br><br>
</body>
</html>