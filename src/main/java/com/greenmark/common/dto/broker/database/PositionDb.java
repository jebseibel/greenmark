package com.greenmark.common.dto.broker.database;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstantsStrategy;
import com.greenmark.common.dto.strategy.decorator.DropcatDtoDecorator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PositionDb</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is also used by the trading client to add additional information it needs when calculating, but does not need to be stored in the database. </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class PositionDb implements Serializable {
	public static final String CLASSNAME = "PositionDb";
	private static final long serialVersionUID = 1L;

	protected String harvestStrategyAcronym;

	protected DropcatDtoDecorator droppedCatInfo;

	public PositionDb() {
		super();
	}

	public PositionDb(int harvestStrategyTypeId, int bucketStrategyStateId, int originBucketTimeframe) {
		this.harvestStrategyAcronym = GmConstantsStrategy.getHarvestStrategyLabel(harvestStrategyTypeId, originBucketTimeframe);
	}

	public PositionDb(PositionDb oldPosition) {
		try {
			if (oldPosition != null) {
				BeanUtils.copyProperties(this, oldPosition);

				if (oldPosition.getDroppedCatInfo() != null)
					this.droppedCatInfo = new DropcatDtoDecorator(oldPosition.getDroppedCatInfo());
			}
		} catch (InvocationTargetException ite) {
			System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
		}
	}

	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public PositionDb(String xmldata) {
//		super(xmldata, trace);
//
//		positionEvents = new ArrayList<>();
//
//		try {
//			harvestStrategyAcronym = UTXmlUtils.getXmlData(xmldata, "HARVEST_STRATEGY_ACRONYM");
//
//			// build the position events
//			String xmlPositionEventList = UTXmlUtils.getXmlData(xmldata, "POSITION_EVENT_LIST");
//			if (UTUtils.isNotNorE(xmlPositionEventList)) {
//				Vector vXmlPositionEvents = UTXmlUtils.getElementsToVector(xmlPositionEventList, "POSITION_EVENT");
//
//				for (Enumeration e = vXmlPositionEvents.elements(); e.hasMoreElements();) {
//					String xml = (String) e.nextElement();
//					PositionEventDbDecorator thisEvent = new PositionEventDbDecorator(xml);
//					this.positionEvents.add(thisEvent);
//				}
//			}
//
//			String dropcatXml = UTXmlUtils.getXmlData(xmldata, "DROPCAT_INFO");
//			if (dropcatXml.equals("") == false)
//				droppedCatInfo = new DropcatDtoDecorator(dropcatXml);
//
//		} catch (Exception e) {
//			log.error("Exception in Current Position xml constructor message: " + e.getMessage());
//		}
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(prefix + "<POSITION>" + endline);
//		stb.append(prefix + toXml(prefix, endline, true) + endline);
//		stb.append(prefix + "</POSITION>" + endline);
//		return stb.toString();
//	}
//
//	public String toXml(String prefix, String endline, boolean includeOrders) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(super.toXml(prefix, endline, includeOrders));
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<HARVEST_STRATEGY_ACRONYM>" + harvestStrategyAcronym + "</HARVEST_STRATEGY_ACRONYM>" + endline);
//
//		if (UTUtils.isNotNorE(this.toXmlPositionEvents(prefix, endline)))
//			stb.append(prefix + this.toXmlPositionEvents(prefix + UTDisplayFormatter.TAB, endline));
//
//		if (droppedCatInfo != null)
//			stb.append(this.droppedCatInfo.toXml(prefix + UTDisplayFormatter.TAB, endline));
//
//		return stb.toString();
//	}
//
//	public final String toXmlPositionEvents(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//
//		stb.append(UTDisplayFormatter.TAB + "<POSITION_EVENT_LIST>" + endline);
//		if (positionEvents != null) {
//			for (PositionEventDbDecorator thisEvent : positionEvents) {
//				stb.append(thisEvent.toXmlWrapper(prefix + UTDisplayFormatter.TAB, endline));
//			}
//		}
//		stb.append(prefix + "</POSITION_EVENT_LIST>" + endline);
//
//		return stb.toString();
//	}

//	public final void addPositionEvent(PositionEventDbDecorator newEvent) {
//		if (positionEvents == null) {
//			positionEvents = new ArrayList();
//		}
//		positionEvents.add(newEvent);
//	}
//
//	// Failsafe method for website
//	public boolean isHasPositionEvents() {
//		if (positionEvents == null || positionEvents.isEmpty()) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	public ArrayList<PositionEventDbDecorator> getOrderedPositionEvents() {
//		ArrayList<PositionEventDbDecorator> returnList = new ArrayList<>();
//		returnList.addAll(positionEvents);
//
//		Collections.sort(returnList, new Comparator<PositionEventDbDecorator>() {
//			@Override
//			public int compare(PositionEventDbDecorator a, PositionEventDbDecorator b) {
//				Long aId = (Long) a.getId();
//				return aId.compareTo((Long) b.getId());
//			}
//		});
//
//		return returnList;
//	}

	// ------------------------------------------------ SETTER/GETTER METHODS ---------------------------------------------------
	public String getHarvestStrategyAcronym() {
		return harvestStrategyAcronym;
	}

	public void setHarvestStrategyAcronym(String harvestStrategyAcronym) {
		this.harvestStrategyAcronym = harvestStrategyAcronym;
	}

	public DropcatDtoDecorator getDroppedCatInfo() {
		return droppedCatInfo;
	}

	public void setDroppedCatInfo(DropcatDtoDecorator droppedCatInfo) {
		this.droppedCatInfo = droppedCatInfo;
	}

}
