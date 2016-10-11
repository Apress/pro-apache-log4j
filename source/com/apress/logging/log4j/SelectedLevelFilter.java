/*
 * SelectedLevelFilter.java
 *
 * Created on 14 November 2002, 20:03
 */

package com.apress.logging.log4j;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.OptionConverter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author  Administrator
 */
public class SelectedLevelFilter extends Filter {
    private ArrayList levels = new ArrayList();
    private boolean acceptOnMatch;
    private String levelsToMatch;
    
    public SelectedLevelFilter() {
    }
    
    public int decide(LoggingEvent event) 
    {
        if(levels.size() == 0)
        {
            return Filter.NEUTRAL;
        }
        Level eventLevel = event.getLevel();
        Iterator iterator = levels.iterator();
        boolean matchFound = false;
        
        while(iterator.hasNext())
        {
            Level level = (Level)iterator.next();
            if(level.equals(eventLevel))
            {
                matchFound = true;
                break;
            }
        }
        
        if(matchFound)
        {
            if(acceptOnMatch)
            {
                return Filter.ACCEPT;
            }else
            {
                return Filter.NEUTRAL;
            }
        }else
        {
            return Filter.DENY;
        }  
    }
    
    /** Getter for property acceptOnMatch.
     * @return Value of property acceptOnMatch.
     */
    public boolean getAcceptOnMatch() {
        return this.acceptOnMatch;
    }
    
    /** Setter for property acceptOnMatch.
     * @param acceptOnMatch New value of property acceptOnMatch.
     */
    public void setAcceptOnMatch(boolean acceptOnMatch) {
        this.acceptOnMatch = acceptOnMatch;
    }
    
    /** Getter for property levelsToMatch.
     * @return Value of property levelsToMatch.
     */
    public String getLevelsToMatch() {
        return this.levelsToMatch;
    }
    
    /** Setter for property levelsToMatch.
     * @param levelsToMatch New value of property levelsToMatch.
     */
    public void setLevelsToMatch(String levelsToMatch) {
        this.levelsToMatch = levelsToMatch;
        if(levelsToMatch !=null)
        {
            StringTokenizer tokenizer = new StringTokenizer(levelsToMatch, ",");
            while(tokenizer.hasMoreTokens())
            {
                String token = tokenizer.nextToken().trim();
                Level level = OptionConverter.toLevel(token, null);
                levels.add(level);
            }
        }
 
    }
    
}
