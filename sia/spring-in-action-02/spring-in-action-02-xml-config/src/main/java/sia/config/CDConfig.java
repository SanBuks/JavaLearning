package sia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sia.domain.interfaces.CompactDisc;
import sia.domain.SgtPeppers;

@Configuration
public class CDConfig {
  @Bean
  public CompactDisc compactDisc() {
    return new SgtPeppers();
  }
}