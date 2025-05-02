package com.emazon.shopping_cart.infraestructure.rest.dto;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/colas")
@Slf4j
public class QueueManager {


    @Autowired
    private JmsTemplate jmsTemplate; // Puedes utilizar esto para enviar mensajes o interactuar con JMS.

    @GetMapping
    public List<String> listQueues() {
        List<String> queueNames = new ArrayList<>();

        MQQueueManager queueManager = null;

        try {
            // Conexión al Queue Manager
            queueManager = new MQQueueManager("PRUEBA1");

            // Enumerar las colas utilizando MQOO_INQUIRE para obtener propiedades de las colas
            MQQueue[] queues = queueManager.getQueues(MQConstants.MQO_INQUIRE); // Aquí se debe usar el método adecuado para enumerar las colas

            // Recorrer todas las colas y agregarlas a la lista
            for (MQQueue queue : queues) {
                queueNames.add(queue.getName());
            }

        } catch (MQException e) {
            log.error("Error al acceder al Queue Manager o listar colas", e);
        } finally {
            try {
                if (queueManager != null) {
                    queueManager.close();
                }
            } catch (MQException e) {
                log.error("Error al cerrar el Queue Manager", e);
            }
        }

        return queueNames;
    }

}
