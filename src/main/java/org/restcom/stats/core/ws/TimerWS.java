/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package org.restcom.stats.core.ws;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.restcom.stats.core.dto.TimerDTO;
import org.restcom.stats.core.service.TimerService;

/**
 *
 * @author Ricardo Limonta
 */
@Path(value = "/timer")
@Named
public class TimerWS {

    @Inject
    private TimerService timerService;
    
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response insertTimer(Map<String, Object> timer) {
        timerService.insertMetric(timer);
        return Response.status(Response.Status.OK).build();
    }  
    
    @GET
    @Path("{timestampFrom}/{timestampTo}/{key}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response retrieveTimersByKey(@PathParam("timestampFrom") long timestampFrom, 
                                        @PathParam("timestampTo") long timestampTo, 
                                        @PathParam("key") String key) {

       List<TimerDTO> histograms = timerService.retrieveMetrics(timestampFrom, timestampTo, key);
       return Response.status(200).entity(histograms).build();
    }
    
    // https://github.com/RestComm/statistics-service/issues/1
    @GET
    @Path("/sum/{timestampFrom}/{timestampTo}/{key}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response retrieveTotalCountersSum(@PathParam("timestampFrom") long timestampFrom, 
                                          @PathParam("timestampTo") long timestampTo, 
                                          @PathParam("key") String key) {

       List<TimerDTO> timers = timerService.retrieveSumMetrics(timestampFrom, timestampTo, key);
       return Response.status(200).entity(timers).build();

    }
}