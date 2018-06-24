<%@include file="/WEB-INF/views/declarations/declarations.jsp" %>
<html>
<%@include file="/WEB-INF/views/head/head.jsp" %>
<body>
	<h1 style="text-align:center">Vorstellungen der Woche</h1>
	<c:forEach items="${presentationWeekScheduleDatas }"
		var="presentationWeekScheduleData">
		<hr />
		<h2>
			<c:out value="${ presentationWeekScheduleData.filmName}"></c:out>
		</h2>
		<table border="1" style="width: 75%">
			<tr>
				<c:forEach
					items="${presentationWeekScheduleData.presentationDayScheduleDatas }"
					var="presentationDayScheduleData">
					<td><c:out value="${presentationDayScheduleData.weekDay }"></c:out>
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach
					items="${presentationWeekScheduleData.presentationDayScheduleDatas }"
					var="presentationDayScheduleData">
					<td><c:forEach
							items="${presentationDayScheduleData.presentations }"
							var="presentation">
							<s:url var="presentationUrl"
								value="/presentation/${presentation.code }" />
							<p>
								<a href="${presentationUrl }"><c:out value="${presentation.startTime }"></c:out></a>
							</p>
						</c:forEach></td>
				</c:forEach>
			</tr>
		</table>
	</c:forEach>
</body>
</html>