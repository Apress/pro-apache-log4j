package com.apress.logging.log4j.customtag;

import org.apache.taglibs.log.LoggerTag;
import org.apache.log4j.Priority;
import com.apress.logging.log4j.CustomLevel;

public class TraceTag extends LoggerTag
 {
    protected Priority getPriority()
    {
        return CustomLevel.TRACE;
    }
}
