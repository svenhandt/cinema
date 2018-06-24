/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13.06.2018 11:02:33                         ---
 * ----------------------------------------------------------------
 */
package org.cinema.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cinema.constants.CinemaConstants;
import org.cinema.jalo.Seat;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem CinemaRoom}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCinemaRoom extends GenericItem
{
	/** Qualifier of the <code>CinemaRoom.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>CinemaRoom.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>CinemaRoom.seatsPlan</code> attribute **/
	public static final String SEATSPLAN = "seatsPlan";
	/** Qualifier of the <code>CinemaRoom.seats</code> attribute **/
	public static final String SEATS = "seats";
	/**
	* {@link OneToManyHandler} for handling 1:n SEATS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Seat> SEATSHANDLER = new OneToManyHandler<Seat>(
	CinemaConstants.TC.SEAT,
	false,
	"cinemaRoom",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(SEATSPLAN, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.seats</code> attribute.
	 * @return the seats
	 */
	public List<Seat> getSeats(final SessionContext ctx)
	{
		return (List<Seat>)SEATSHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.seats</code> attribute.
	 * @return the seats
	 */
	public List<Seat> getSeats()
	{
		return getSeats( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.seats</code> attribute. 
	 * @param value the seats
	 */
	public void setSeats(final SessionContext ctx, final List<Seat> value)
	{
		SEATSHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.seats</code> attribute. 
	 * @param value the seats
	 */
	public void setSeats(final List<Seat> value)
	{
		setSeats( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to seats. 
	 * @param value the item to add to seats
	 */
	public void addToSeats(final SessionContext ctx, final Seat value)
	{
		SEATSHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to seats. 
	 * @param value the item to add to seats
	 */
	public void addToSeats(final Seat value)
	{
		addToSeats( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from seats. 
	 * @param value the item to remove from seats
	 */
	public void removeFromSeats(final SessionContext ctx, final Seat value)
	{
		SEATSHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from seats. 
	 * @param value the item to remove from seats
	 */
	public void removeFromSeats(final Seat value)
	{
		removeFromSeats( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.seatsPlan</code> attribute.
	 * @return the seatsPlan
	 */
	public Media getSeatsPlan(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, SEATSPLAN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CinemaRoom.seatsPlan</code> attribute.
	 * @return the seatsPlan
	 */
	public Media getSeatsPlan()
	{
		return getSeatsPlan( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.seatsPlan</code> attribute. 
	 * @param value the seatsPlan
	 */
	public void setSeatsPlan(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, SEATSPLAN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CinemaRoom.seatsPlan</code> attribute. 
	 * @param value the seatsPlan
	 */
	public void setSeatsPlan(final Media value)
	{
		setSeatsPlan( getSession().getSessionContext(), value );
	}
	
}
