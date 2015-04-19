package de.jcup.code2doc.core.decorate;

import java.util.ArrayList;
import java.util.List;

import de.jcup.code2doc.api.Element.MarkupType;
import de.jcup.code2doc.core.internal.util.MarkupSupport;
import de.jcup.code2doc.core.internal.util.Validation;

/**
 * Abstract implementation of a mark up text decorator. Implementation does allow only mark up types supported by registered {@link MarkupSupport} instances
 * @author Albert Tregnaghi
 *
 */
public abstract class AbstractMarkupTextDecorator implements MarkupTextDecorator{

	private List<MarkupSupport> supportedTypes = new ArrayList<MarkupSupport>();
	
	public AbstractMarkupTextDecorator(){
		MarkupTextDecoratorSetup setup = new MarkupTextDecoratorSetup();
		setup(setup);
	}

	/* TODO de-jcup, 19.04.2015: what about giving this to users outside - as an API to customize mark up supporting?*/
	protected abstract void setup(MarkupTextDecoratorSetup setup);
	
	/***
	 * A special setup for this decorator
	 * @author Albert Tregnaghi
	 *
	 */
	protected class MarkupTextDecoratorSetup{
		/**
		 * Add a supported type for this mark up text decorator
		 * @param support
		 * @throws IllegalArgumentException if a support is added supporting a {@link MarkupType} already supported by a former registered {@link MarkupSupport}.
		 */
		public void register(MarkupSupport support){
			MarkupType type = support.getMarkupType();
			if (isSupported(type)){
				/* already supported...*/
				MarkupSupport alreadyRegistered = getSupport(type);
				throw new IllegalArgumentException("Type:"+ type +" is already handled by former installed "+alreadyRegistered.getClass().getSimpleName());
			}
			supportedTypes.add(support);
		}
		
	}
	
	@Override
	public final String decorate(String text) {
		return decorate(MarkupType.getDefault(), text);
	}
	
	/**
	 * Returns true when given type is supported by this decorator
	 * @param type
	 * @return <code>true</code> when supported otherwise <code>false</code>
	 */
	protected boolean isSupported(MarkupType type){
		if (type==null){
			/* null is never supported...*/
			return false;
		}
		return getSupport(type)!=null;
	}

	
	/**
	 * Returns support 
	 * @param type - mark up type to search support
	 * @return support for given type or <code>null</code> if not found
	 */
	protected MarkupSupport getSupport(MarkupType type){
		if (type==null){
			return null;
		}
		for (MarkupSupport support: supportedTypes){
			if (type.equals(support.getMarkupType())){
				return support;
			}
		}
		/* not found */
		return null;
	}
	
	public final String decorate(MarkupType type, String text){
		/* validation*/
		Validation.notNull(type, "Type may not be null!");
		if (! isSupported(type)){
			throw new IllegalArgumentException("The type "+type+" is not supported by markup decorator:"+getClass().getSimpleName());
		}
		
		return getSupport(type).handleMarkup(text);
	}
	

}
