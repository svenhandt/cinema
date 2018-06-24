<%@include file="/WEB-INF/views/declarations/declarations.jsp"%>
<html>
<%@include file="/WEB-INF/views/head/head.jsp"%>
<body>
	<h2 style="text-align: center">Ihre Buchung</h2>
	<br/>
	<h4>Vielen Dank f&auml;r Ihre Buchung. Nachfolgend Ihre Buchungsinformationen</h4>
	<hr/>
	<p>Bestellnummer: <c:out value="${orderInformationData.orderNumber }"/>
	<br/>
	<h5>Anschrift:</h5>
	<c:set var="addressInfoData" value="${orderInformationData.addressInfoData }"/>
	<p><c:out value="${addressInfoData.firstName }" /> <c:out value="${addressInfoData.lastName }" /> </p>
	<p><c:out value="${addressInfoData.street }" /> <c:out value="${addressInfoData.streetNumber }" /> </p>
	<p><c:out value="${addressInfoData.postalCode }" /> <c:out value="${addressInfoData.town }" /> </p>
	<br/>
	<h5>Zahlung:</h5>
	<p>Kreditkarte: <c:out value="${orderInformationData.creditCardNumber }"/>
	<br/>
	<h5>Ihre Buchung:</h5>
	<c:set var="presentationData" value="${orderInformationData.presentationData }"/>
	<p>Film: <c:out value="${presentationData.film.name }"/></p>
	<p>Saal: <c:out value="${presentationData.room.name }"/></p>
	<p>Beginn: <c:out value="${presentationData.startTime }"/></p>
	<p>Gebuchte Sitzpl&auml;tze:</p>
	<ul>
		<c:forEach items="${orderInformationData.seats }" var="seat">
			<li>Reihe: <c:out value="${seat.seatRow }" />, Platz: <c:out value="${seat.positionInSeatRow }" /></li>
		</c:forEach>
	</ul>
	<hr/>
	<div>
		<s:url var="getbookingpage" value="/getbookingpage" />
		<s:url var="getstartpage" value="/" />
		<span>Gesamtpreis: <c:out value="${orderInformationData.totalPrice }"/></span>
		<a href="${getstartpage }" style="position: absolute; right: 20px; font-size: 18px">Zur&uuml;ck zur &Uuml;bersicht</a>
	</div>
	
	
</body>
</html>