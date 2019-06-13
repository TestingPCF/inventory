package com.hcl.cloud.inventory.component;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hcl.cloud.product.event.InventoryAddedEvent;
import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.repository.InventoryRepository;


/*
* The @ProcessingGroup annotation hooks up this class up to an Axon Event Processor Group.
* The @EventHandler annotated method handles the Events of a given type.
*
* When the events come in, we persist a 'Product' entity to the JPA repository
* created for us by Spring Data Repositories.
*
* We have to organise the event handler into a Processor group as by default, processors read from the EventBus not AMQP,
* so we need to reconfigure things a bit - we need to add our event handler to a processor that reads from a Rabbit queue.
*
* So we annotate the class with @ProcessorGroup(“amqpEvents”) to configure the event processor (note default processor
* would use the package-name). We also need to add to the application.properties the following setting
* “axon.eventhandling.processors.amqpEvents.source=complaintEventsMethod”. You'll see this property in GitHub as that's
* where our configuration comes from for this app as it's been externalised by Spring Cloud Config.
*
* Note that the “complaintEventsMethod” keyword in the properties comes from (and must match) the @Bean name of the
* complaintEventsMethod(Serializer serializer) method in the AxonConfiguration class!
*/

@Component
@ProcessingGroup("amqpEvents")
public class EventProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(EventProcessor.class);

    private final InventoryRepository repo;

    public EventProcessor(InventoryRepository repository) {
        this.repo = repository;
    }
   
    @EventHandler // Mark this method as an Axon Event Handler
    public void on(InventoryAddedEvent inventoryAddedEvent) {
    	LOG.info(" Start A product was added! Id={} Name={}", inventoryAddedEvent.getSkuCode(), inventoryAddedEvent.getQuantity());
        repo.save(new InventoryItem(inventoryAddedEvent.getSkuCode(),inventoryAddedEvent.getQuantity(),inventoryAddedEvent.getStatus()));
        LOG.info("A product was added! Id={} Name={}", inventoryAddedEvent.getSkuCode(), inventoryAddedEvent.getQuantity());
    }
}
