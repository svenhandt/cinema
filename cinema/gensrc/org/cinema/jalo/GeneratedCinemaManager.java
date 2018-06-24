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
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.cinema.constants.CinemaConstants;
import org.cinema.jalo.CinemaRoom;
import org.cinema.jalo.Film;
import org.cinema.jalo.Presentation;
import org.cinema.jalo.Seat;

/**
 * Generated class for type <code>CinemaManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCinemaManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("seat", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrderEntry", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public CinemaRoom createCinemaRoom(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( CinemaConstants.TC.CINEMAROOM );
			return (CinemaRoom)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CinemaRoom : "+e.getMessage(), 0 );
		}
	}
	
	public CinemaRoom createCinemaRoom(final Map attributeValues)
	{
		return createCinemaRoom( getSession().getSessionContext(), attributeValues );
	}
	
	public Film createFilm(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( CinemaConstants.TC.FILM );
			return (Film)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Film : "+e.getMessage(), 0 );
		}
	}
	
	public Film createFilm(final Map attributeValues)
	{
		return createFilm( getSession().getSessionContext(), attributeValues );
	}
	
	public Presentation createPresentation(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( CinemaConstants.TC.PRESENTATION );
			return (Presentation)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Presentation : "+e.getMessage(), 0 );
		}
	}
	
	public Presentation createPresentation(final Map attributeValues)
	{
		return createPresentation( getSession().getSessionContext(), attributeValues );
	}
	
	public Seat createSeat(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( CinemaConstants.TC.SEAT );
			return (Seat)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Seat : "+e.getMessage(), 0 );
		}
	}
	
	public Seat createSeat(final Map attributeValues)
	{
		return createSeat( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return CinemaConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.seat</code> attribute.
	 * @return the seat
	 */
	public Seat getSeat(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (Seat)item.getProperty( ctx, CinemaConstants.Attributes.AbstractOrderEntry.SEAT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.seat</code> attribute.
	 * @return the seat
	 */
	public Seat getSeat(final AbstractOrderEntry item)
	{
		return getSeat( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.seat</code> attribute. 
	 * @param value the seat
	 */
	public void setSeat(final SessionContext ctx, final AbstractOrderEntry item, final Seat value)
	{
		item.setProperty(ctx, CinemaConstants.Attributes.AbstractOrderEntry.SEAT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.seat</code> attribute. 
	 * @param value the seat
	 */
	public void setSeat(final AbstractOrderEntry item, final Seat value)
	{
		setSeat( getSession().getSessionContext(), item, value );
	}
	
}
