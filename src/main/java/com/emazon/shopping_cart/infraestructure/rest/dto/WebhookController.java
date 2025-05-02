package com.emazon.shopping_cart.infraestructure.rest.dto;


import com.emazon.shopping_cart.infraestructure.entities.TesterClassEntity;
import com.emazon.shopping_cart.infraestructure.repository.TesterClassRepository;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.PaymentMethod;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.Purchase;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.RefundRequest;
import com.emazon.shopping_cart.infraestructure.rest.dto.clases.User;
import com.emazon.shopping_cart.infraestructure.rest.dto.repository.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@RestController
@RequestMapping("/api/webhook")
@Slf4j
public class WebhookController {

    public static final int INITIAL_VALUE = 0;
    @Autowired
    private TesterClassRepository testerClassRepository;


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private NotificationService notificationService;

    private final AtomicInteger conteo = new AtomicInteger(INITIAL_VALUE);

    private static final int MAXIMO_EJECUCIONES = 20;


    @GetMapping("/iniciar-pruebas2")
    public ResponseEntity<String> iniciarPruebas2() {
        String url = "https://wlp.pruebas.proteccion.com.co/api/conceptos/solicitud/2ed97af0-fb68-11ef-ba5d-994dc36c4e99";
        String username = "test_user";
        String password = "Inicio2016";

        // Generar autenticación Basic Auth
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al conectar con el endpoint: " + e.getMessage());
        }
    }

    @PostMapping("/iniciar-pruebas")
    public ResponseEntity<Void> iniciarPruebas() {
        for (int i = 0; i < MAXIMO_EJECUCIONES; i++) {
            String uuid = UUID.randomUUID().toString();
            String eventJson = "{\"evento\":{\"id\":\"6b4a9900-fb6e-11ef-a7ca-994dc36c4e99\",\"uuid\":\"" + uuid + "\",\"idTransaccion\":\"2ed97af0-fb68-11ef-ba5d-994dc36c4e99\",\"nombre\":\"RadicacionIncapacidadCreada\",\"version\":1.0,\"aplicacionEmisora\":\"SIT\",\"usuario\":{\"dni\":\"\",\"ip\":\"10.67.64.85, 10.80.18.117\",\"nombre\":\"test_user\",\"canal\":\"INTERNET\",\"telefono\":\"\"},\"dni\":{\"tipo\":\"CC\",\"identificacion\":\"1001444887\"}},\"timestamp\":\"2025-03-07T11:08:33.680-05:00\",\"mensaje\":{\"encabezado\":{\"nombreUsuario\":\"test_user\",\"fechaElaboracion\":\"2025-03-07T11:08:33.647-05:00\",\"oficina\":\"ODS LA MINA\",\"codigoOficina\":\"9040\",\"tipoTramite\":\"Solicitud incapacidad Temporal\",\"numeroSolicitud\":\"2ed6653-fb68-11ef-ba5d-994dc36c4e99\",\"numeroSolicitudIPS\":null,\"estadoSolicitud\":\"SOLICITUD_RADICADA\",\"fechaActualizacion\":null,\"tieneAccionLegal\":false},\"datosPersonales\":{\"estado\":null,\"tipoIdentificacion\":\"CC\",\"numeroIdentificacion\":\"1001444887\",\"primerNombre\":\"WILLINGTON\",\"segundoNombre\":\"FERNANDA\",\"primerApellido\":\"ORTIZ\",\"segundoApellido\":\"FUERTES\",\"direccion\":\"KR 50     45      43  B 5 OF 224 C.C. CORDOBA\",\"municipio\":{\"nombre\":\"PUERTO TRIUNFO - ANTIOQUIA - Colombia\",\"id\":\"05591\"},\"correoElectronico\":\"willington@gmail.com\",\"numeroTelefono\":\"5618528\",\"numeroCelular\":\"3115674321\",\"genero\":\"M\",\"fechaNacimiento\":\"1996-04-09T00:00:00.000-05:00\",\"estadoCivil\":\"2\",\"otroNumeroTelefono\":\"\",\"gradoEscolaridad\":\"2\",\"eps\":{\"nombre\":\"COMFACOR EPS-CCF DE CORDOBA\",\"id\":\"891080005\"},\"tipoAfiliadoEps\":\"Cotizante\",\"arl\":{\"nombre\":\"SEGUROS DE RIESGOS LABORALES SURAMERICANA S.A..\",\"nit\":\"800256161\"},\"autorizaEnvioSMS\":false,\"autorizaEnvioCorreo\":false},\"datosSolicitante\":{\"tipoSolicitante\":\"AFILIADO\",\"tipoIdentificacion\":\"CC\",\"numeroIdentificacion\":\"1001444887\",\"primerNombre\":\"WILLINGTON\",\"segundoNombre\":\"FERNANDA\",\"primerApellido\":\"ORTIZ\",\"segundoApellido\":\"FUERTES\",\"correoElectronico\":\"willington@gmail.com\",\"direccion\":\"KR 50     45      43  B 5 OF 224 C.C. CORDOBA\",\"municipio\":{\"nombre\":\"PUERTO TRIUNFO - ANTIOQUIA - Colombia\",\"id\":\"05591\"},\"numeroCelular\":\"3115674321\",\"numeroTelefono\":\"5618528\",\"genero\":\"M\"},\"beneficiarioPago\":{\"tipoBeneficiario\":\"AFILIADO\",\"tipoIdentificacion\":null,\"numeroIdentificacion\":null,\"razonSocial\":null,\"primerNombre\":null,\"segundoNombre\":null,\"primerApellido\":null,\"segundoApellido\":null},\"guion\":{\"esPensionadoOtraEntidad\":false,\"esPensionadoPorARL\":false,\"nombreEmpresa\":\"colsultamos\",\"tiempoLaborado\":\"8 años\",\"cargoEmpresa\":\"celador\",\"tiempoEnElCargo\":\"7 AÑOS\",\"enfermedadIncapacidad\":\"dolor de columna\",\"esConsecuenciaAccidente\":false,\"comoSucedioAccidente\":null,\"esHorarioLaboral\":false,\"esAccidenteTransito\":false,\"tieneReporte\":false,\"otrasEnfermedades\":[],\"fechaReintegro\":null,\"nombreOtraEntidad\":null,\"otraEnfermedad\":null,\"tiempoEnfermedad\":\"7\",\"causaAccidente\":\"04\",\"seReintegroLaboralmente\":false},\"documentacionRequerida\":{\"cedula\":false,\"certificadoIncapacidades\":false,\"autorizacionPagoTerceros\":false,\"actaCurador\":false,\"poder\":false,\"certificadoCuentaBancaria\":false},\"cuentasBancarias\":{\"id\":null,\"entidadBancaria\":\"0063\",\"tipoDeCuenta\":\"A\",\"numeroDeCuenta\":\"12345678\"},\"beneficiariosAfiliado\":[]}}";

            try {
                jmsTemplate.convertAndSend("Q.SUBS.APP.REPORTE_INCAPACIDAD", eventJson);
                testerClassRepository.save(new TesterClassEntity(uuid));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<Void> recibirRespuesta(@RequestBody TesterClass testerClass) {

        testerClass.setUuid(testerClass.getUuid().trim());

            log.info("RESPUESTA RECIBIDA EXITOSAMENTE: " + testerClass);

            TesterClassEntity entity = testerClassRepository.findByUuid(testerClass.getUuid()).orElseThrow();
            entity.setDescripcion(testerClass.getMessage());
            entity.setExito(testerClass.getSuccess());
            testerClassRepository.save(entity);

            log.info("RESPUESTA PROCESADA EXITOSAMENTE: " + testerClass);
            return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<ResponseCasos> listarCasos() {
        List<TesterClassEntity> casos = testerClassRepository.findAll()
                .stream()
                .filter(caso -> caso.getDescripcion() != null) // Filtrar casos con descripción null
                .collect(Collectors.toList());

        Map<String, List<TesterClassEntity>> casosPorMessage = casos.stream()
                .collect(Collectors.groupingBy(TesterClassEntity::getDescripcion));

        Map<String, Integer> casosConteoPorMessage = casosPorMessage.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));

        int casosSatisfactorios = (int) casos.stream().filter(TesterClassEntity::isExito).count();
        int casosFallidos = casos.size() - casosSatisfactorios;

        ResponseCasos response = new ResponseCasos();
        response.setCasosPorMessage(casosPorMessage);
        response.setCasosConteoPorMessage(casosConteoPorMessage);
        response.setCasosSatisfactorios(casosSatisfactorios);
        response.setCasosFallidos(casosFallidos);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/refunds")
    public ResponseEntity<Void> processRefund(@RequestBody RefundRequestDto requestDto) {

        try {
            String userId = requestDto.getUserId();
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            User user = userOpt.get();

            List<Purchase> eligiblePurchases = purchaseService.getPurchasesEligibleForRefund(user.getId());
            if (eligiblePurchases.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            double totalRefund = eligiblePurchases.stream()
                    .mapToDouble(Purchase::getAmount)
                    .sum();

            Optional<PaymentMethod> paymentMethodOpt = paymentService.getPreferredPaymentMethod(user.getId());
            if (paymentMethodOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
            }

            RefundRequest refundRequest = new RefundRequest();
            refundRequest.setUserId(user.getId());
            refundRequest.setAmount(totalRefund);
            refundRequest.setStatus("PENDIENTE");
            refundRequest.setCreatedAt(LocalDateTime.now());

            refundRepository.save(refundRequest);

            notificationService.notifyUserOfRefund(user, refundRequest);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado");
        }
    }






}

 class RefundRequestDto {
    private String userId;

    // Getters y Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
