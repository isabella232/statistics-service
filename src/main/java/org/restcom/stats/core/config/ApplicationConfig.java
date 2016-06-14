/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.restcom.stats.core.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.restcom.stats.core.service.CounterService;
import org.restcom.stats.core.service.GaugeService;
import org.restcom.stats.core.service.HistogramService;
import org.restcom.stats.core.service.MeterService;
import org.restcom.stats.core.service.TimerService;

/**
 *
 * @author rlimonta
 */
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {
     @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> returnValue = new HashSet<Class<?>>();
        returnValue.add(CounterService.class);
        returnValue.add(GaugeService.class);
        returnValue.add(HistogramService.class);
        returnValue.add(MeterService.class);
        returnValue.add(TimerService.class);
        return returnValue;
    }
}