package sia;

import java.io.PrintStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sia.domain.BraveKnight;
import sia.domain.interfaces.Knight;
import sia.domain.interfaces.Quest;
import sia.domain.SlayDragonQuest;

@Configuration
public class KnightConfig {

  @Bean
  public Knight knight() {
    return new BraveKnight(quest());
  }

  @Bean
  public Quest quest() {
    return new SlayDragonQuest(stream());
  }

  @Bean
  public PrintStream stream() {
    return new FakePrintStream();
  }

}
