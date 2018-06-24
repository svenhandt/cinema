<%@include file="/WEB-INF/views/declarations/declarations.jsp"%>
<html>
<%@include file="/WEB-INF/views/head/head.jsp"%>
<body>
	<h2 style="text-align: center">Ihre Buchung</h2>
	<br/>
	<s:url var="placebooking" value="/placebooking" />
	<form:form action="${placebooking }" commandName="customerInfoData" >
		
		<h4>Bitte geben Sie Ihre pers&ouml;nlichen Daten ein</h4>
		<hr/>
		<table>
			<tr>
				<td><form:label path="addressInfoData.firstName">Vorname* </form:label></td>
				<td>
					<form:errors path="addressInfoData.firstName" element="div" cssClass="error" />
					<form:input path="addressInfoData.firstName" size="30%"/>
				</td>
			</tr>
			<tr>
				<td><form:label path="addressInfoData.lastName">Nachname* </form:label></td>
				<td>
					<form:errors path="addressInfoData.lastName" element="div" cssClass="error" />
					<form:input path="addressInfoData.lastName" size="30%"/>
				</td>
			</tr>
			<tr>
				<td><label>Strasse*/Nr.* <label></td>
				<td>
					<form:errors path="addressInfoData.street" element="div" cssClass="error" />
					<form:errors path="addressInfoData.streetNumber" element="div" cssClass="error" />
					<form:input path="addressInfoData.street"/>
					<form:input path="addressInfoData.streetNumber" size="5%"/>
				</td>
			</tr>
			<tr>
				<td><label>PLZ*/Ort* <label></td>
				<td>
					<form:errors path="addressInfoData.postalCode" element="div" cssClass="error" />
					<form:errors path="addressInfoData.town" element="div" cssClass="error" />
					<form:input path="addressInfoData.postalCode" size="5%"/>
					<form:input path="addressInfoData.town"/>
				</td>
			</tr>
			<tr>
				<td><form:label path="addressInfoData.emailAddress">E-Mail-Adresse </form:label></td>
				<td>
					<form:errors path="addressInfoData.emailAddress" element="div" cssClass="error" />
					<form:input path="addressInfoData.emailAddress" size="30%"/>
				</td>
			</tr>
		</table>
		<br/>
		<h4>Bitte geben Sie Ihre Kreditkartendaten ein</h4>
		<hr/>
		<table>
			<tr>
				<td><form:label path="creditCardInfo.cardOwner">Karteninhaber* </form:label></td>
				<td>
					<form:errors path="creditCardInfo.cardOwner" element="div" cssClass="error" />
					<form:input path="creditCardInfo.cardOwner" size="30%"/>
				</td>
			</tr>
			<tr>
				<td><form:label path="creditCardInfo.cardNumber">Kartennummer* </form:label></td>
				<td>
					<form:errors path="creditCardInfo.cardNumber" element="div" cssClass="error" />
					<form:input path="creditCardInfo.cardNumber" size="30%"/>
				</td>
			</tr>
			<tr>
				<td><label>G&uuml;ltig bis*</label></td>
				<td>
					<form:errors path="creditCardInfo.validityMonth" element="div" cssClass="error" />
					<form:select path="creditCardInfo.validityMonth" items="${customerInfoData.creditCardInfo.validityMonthsList }"/>
					<form:select path="creditCardInfo.validityYear" items="${customerInfoData.creditCardInfo.validityYearsList }"/>
				</td>
			</tr>
			<tr>
				<td><form:label path="creditCardInfo.cvcCode">Code* </form:label></td>
				<td>
					<form:errors path="creditCardInfo.cvcCode" element="div" cssClass="error" />
					<form:input path="creditCardInfo.cvcCode" size="5%"/>
				</td>
			</tr>
		</table>
		
		<hr/>
		<input type="submit" value="Jetzt buchen" />
	
	</form:form>
	
</body>
</html>