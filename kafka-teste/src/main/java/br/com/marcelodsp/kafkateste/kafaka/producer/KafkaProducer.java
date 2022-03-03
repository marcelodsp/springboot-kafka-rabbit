package br.com.marcelodsp.kafkateste.kafaka.producer;

import java.util.UUID;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.marcelodsp.kafkateste.config.MyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MyConfig myConfig;

    @Bean
    public NewTopic testTopic() {
        return new NewTopic(myConfig.getKafka().getTopicName(), 3, (short) 1);
    }

    public void sendToKafka(Object objeto) {
        final String key = UUID.randomUUID().toString();

        kafkaTemplate.send(myConfig.getKafka().getTopicName(), key, objeto)
                .addCallback(
                        success -> log.info("Mensagem enviada ao Kafka {}", success),
                        error -> log.error("Erro ao enviar mensagem para o Kafka {}",
                                error.getMessage()));
    }

}
