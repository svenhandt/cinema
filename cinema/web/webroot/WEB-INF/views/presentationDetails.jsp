<%@include file="/WEB-INF/views/declarations/declarations.jsp"%>
<html>
<%@include file="/WEB-INF/views/head/head.jsp"%>
<body>
	<h2 style="text-align: center">
		<c:out
			value="${presentationData.film.name },
		${presentationData.room.name }"></c:out>
	</h2>
	<h4 style="text-align: center">Bitte w&auml;hlen Sie Ihre(n)
		Platz/Pl&auml;tze</h4>
	<hr />
	<p style="text-align: center">====================================================================</p>
	<br />
	<br />
	<c:if test="${presentationData.room.seatRows != null }">
		<s:url var="actualizeCartUrl" value="/actualizecart" />
		<form action="${actualizeCartUrl }" method="post" id="addpresentationform">
			<table style="width: 100%">
				<c:forEach items="${presentationData.room.seatRows }" var="seatRow">
					<tr>
						<td><c:out value="  ${seatRow.seatRowNumber }"></c:out></td>
						<c:forEach items="${seatRow.seatRowPositions }"
							var="seatRowPosition">
							<td><c:if test="${seatRowPosition.hasSeat }">
									<c:set var="checkboxid"
										value="seat_${seatRow.seatRowNumber }_${seatRowPosition.rowPositionNumber }" />
									<c:choose>
										<c:when test="${seatRowPosition.isOccupied}">
											<c:set var="checkboxEnabled" value="disabled"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="checkboxEnabled" value=""></c:set>
										</c:otherwise>
									</c:choose>
									<input type="checkbox" id="${checkboxid }"
										name="seat" value="${checkboxid }" ${checkboxEnabled} />
								</c:if></td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="presentationcode"
				value="${presentationData.code }" />
		</form>
	</c:if>
	<hr />
	<div>
		<s:url var="getbookingpage" value="/getbookingpage" />
		<span>Gesamtsumme: </span><span id="totalsum"></span>
		<a href="${getbookingpage }" id="gotobooking" style="position: absolute; right: 20px; font-size: 18px">Buchen</a>
	</div>
</body>
</html>