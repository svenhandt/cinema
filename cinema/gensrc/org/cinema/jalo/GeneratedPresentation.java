/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13.06.2018 11:02:33                         ---
 * ----------------------------------------------------------------
 */
package org.cinema.jalo;

import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.cinema.constants.CinemaConstants;
import org.cinema.jalo.CinemaRoom;
import org.cinema.jalo.Film;

/**
 * Generated class for type {@link de.hybris.platform.jalo.product.Product Presentation}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPresentation extends Product
{
	/** Qualifier of the <code>Presentation.startDate</code> attribute **/
	public static final String STARTDATE = "startDate";
	/** Qualifier of the <code>Presentation.film</code> attribute **/
	public static final String FILM = "film";
	/** Qualifier of the <code>Presentation.room</code> attribute **/
	public static final String ROOM = "room";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Product.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(STARTDATE, AttributeMode.INITIAL);
		tmp.put(FILM, AttributeMode.INITIAL);
		tmp.put(ROOM, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Presentation.film</code> attribute.
	 * @return the film
	 */
	public Film getFilm(final SessionContext ctx)
	{
		return (Film)getProperty( ctx, FILM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Presentation.film</code> attribute.
	 * @return the film
	 */
	public Film getFilm()
	{
		return getFilm( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Presentation.film</code> attribute. 
	 * @param value the film
	 */
	public void setFilm(final SessionContext ctx, final Film value)
	{
		setProperty(ctx, FILM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Presentation.film</code> attribute. 
	 * @param value the film
	 */
	public void setFilm(final Film value)
	{
		setFilm( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Presentation.room</code> attribute.
	 * @return the room
	 */
	public CinemaRoom getRoom(final SessionContext ctx)
	{
		return (CinemaRoom)getProperty( ctx, ROOM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Presentation.room</code> attribute.
	 * @return the room
	 */
	public CinemaRoom getRoom()
	{
		return getRoom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Presentation.room</code> attribute. 
	 * @param value the room
	 */
	public void setRoom(final SessionContext ctx, final CinemaRoom value)
	{
		setProperty(ctx, ROOM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Presentation.room</code> attribute. 
	 * @param value the room
	 */
	public void setRoom(final CinemaRoom value)
	{
		setRoom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Presentation.startDate</code> attribute.
	 * @return the startDate
	 */
	public Date getStartDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, STARTDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Presentation.startDate</code> attribute.
	 * @return the startDate
	 */
	public Date getStartDate()
	{
		return getStartDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Presentation.startDate</code> attribute. 
	 * @param value the startDate
	 */
	public void setStartDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, STARTDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Presentation.startDate</code> attribute. 
	 * @param value the startDate
	 */
	public void setStartDate(final Date value)
	{
		setStartDate( getSession().getSessionContext(), value );
	}
	
}
