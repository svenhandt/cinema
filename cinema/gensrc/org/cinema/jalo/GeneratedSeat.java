/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13.06.2018 11:02:33                         ---
 * ----------------------------------------------------------------
 */
package org.cinema.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.cinema.constants.CinemaConstants;
import org.cinema.jalo.CinemaRoom;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Seat}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSeat extends GenericItem
{
	/** Qualifier of the <code>Seat.seatRow</code> attribute **/
	public static final String SEATROW = "seatRow";
	/** Qualifier of the <code>Seat.positionInSeatRow</code> attribute **/
	public static final String POSITIONINSEATROW = "positionInSeatRow";
	/** Qualifier of the <code>Seat.abstractOrderEntry</code> attribute **/
	public static final String ABSTRACTORDERENTRY = "abstractOrderEntry";
	/** Qualifier of the <code>Seat.cinemaRoom</code> attribute **/
	public static final String CINEMAROOM = "cinemaRoom";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n CINEMAROOM's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedSeat> CINEMAROOMHANDLER = new BidirectionalOneToManyHandler<GeneratedSeat>(
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
		tmp.put(SEATROW, AttributeMode.INITIAL);
		tmp.put(POSITIONINSEATROW, AttributeMode.INITIAL);
		tmp.put(ABSTRACTORDERENTRY, AttributeMode.INITIAL);
		tmp.put(CINEMAROOM, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.abstractOrderEntry</code> attribute.
	 * @return the abstractOrderEntry
	 */
	public AbstractOrderEntry getAbstractOrderEntry(final SessionContext ctx)
	{
		return (AbstractOrderEntry)getProperty( ctx, ABSTRACTORDERENTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.abstractOrderEntry</code> attribute.
	 * @return the abstractOrderEntry
	 */
	public AbstractOrderEntry getAbstractOrderEntry()
	{
		return getAbstractOrderEntry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.abstractOrderEntry</code> attribute. 
	 * @param value the abstractOrderEntry
	 */
	public void setAbstractOrderEntry(final SessionContext ctx, final AbstractOrderEntry value)
	{
		setProperty(ctx, ABSTRACTORDERENTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.abstractOrderEntry</code> attribute. 
	 * @param value the abstractOrderEntry
	 */
	public void setAbstractOrderEntry(final AbstractOrderEntry value)
	{
		setAbstractOrderEntry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.cinemaRoom</code> attribute.
	 * @return the cinemaRoom
	 */
	public CinemaRoom getCinemaRoom(final SessionContext ctx)
	{
		return (CinemaRoom)getProperty( ctx, CINEMAROOM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.cinemaRoom</code> attribute.
	 * @return the cinemaRoom
	 */
	public CinemaRoom getCinemaRoom()
	{
		return getCinemaRoom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.cinemaRoom</code> attribute. 
	 * @param value the cinemaRoom
	 */
	public void setCinemaRoom(final SessionContext ctx, final CinemaRoom value)
	{
		CINEMAROOMHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.cinemaRoom</code> attribute. 
	 * @param value the cinemaRoom
	 */
	public void setCinemaRoom(final CinemaRoom value)
	{
		setCinemaRoom( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		CINEMAROOMHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.positionInSeatRow</code> attribute.
	 * @return the positionInSeatRow
	 */
	public Integer getPositionInSeatRow(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, POSITIONINSEATROW);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.positionInSeatRow</code> attribute.
	 * @return the positionInSeatRow
	 */
	public Integer getPositionInSeatRow()
	{
		return getPositionInSeatRow( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.positionInSeatRow</code> attribute. 
	 * @return the positionInSeatRow
	 */
	public int getPositionInSeatRowAsPrimitive(final SessionContext ctx)
	{
		Integer value = getPositionInSeatRow( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.positionInSeatRow</code> attribute. 
	 * @return the positionInSeatRow
	 */
	public int getPositionInSeatRowAsPrimitive()
	{
		return getPositionInSeatRowAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.positionInSeatRow</code> attribute. 
	 * @param value the positionInSeatRow
	 */
	public void setPositionInSeatRow(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, POSITIONINSEATROW,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.positionInSeatRow</code> attribute. 
	 * @param value the positionInSeatRow
	 */
	public void setPositionInSeatRow(final Integer value)
	{
		setPositionInSeatRow( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.positionInSeatRow</code> attribute. 
	 * @param value the positionInSeatRow
	 */
	public void setPositionInSeatRow(final SessionContext ctx, final int value)
	{
		setPositionInSeatRow( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.positionInSeatRow</code> attribute. 
	 * @param value the positionInSeatRow
	 */
	public void setPositionInSeatRow(final int value)
	{
		setPositionInSeatRow( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.seatRow</code> attribute.
	 * @return the seatRow
	 */
	public Integer getSeatRow(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, SEATROW);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.seatRow</code> attribute.
	 * @return the seatRow
	 */
	public Integer getSeatRow()
	{
		return getSeatRow( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.seatRow</code> attribute. 
	 * @return the seatRow
	 */
	public int getSeatRowAsPrimitive(final SessionContext ctx)
	{
		Integer value = getSeatRow( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Seat.seatRow</code> attribute. 
	 * @return the seatRow
	 */
	public int getSeatRowAsPrimitive()
	{
		return getSeatRowAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.seatRow</code> attribute. 
	 * @param value the seatRow
	 */
	public void setSeatRow(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, SEATROW,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.seatRow</code> attribute. 
	 * @param value the seatRow
	 */
	public void setSeatRow(final Integer value)
	{
		setSeatRow( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.seatRow</code> attribute. 
	 * @param value the seatRow
	 */
	public void setSeatRow(final SessionContext ctx, final int value)
	{
		setSeatRow( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Seat.seatRow</code> attribute. 
	 * @param value the seatRow
	 */
	public void setSeatRow(final int value)
	{
		setSeatRow( getSession().getSessionContext(), value );
	}
	
}
