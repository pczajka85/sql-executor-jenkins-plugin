Instalacja plugina:
1. Budujemy projekt poleceniem: clean install -Pjenkins. Powstanie plik z rozszerzeniem .hpi
2. W Jenkinsie wchodzimy w 'Manage Jenkins' -> 'Manage Plugins' -> 'Advanced'
3. W 'Upload Plugin' ładujemy plik .hpi.

Konfiguracja pluginu:
1. W konfiguracji projektu klikamy 'Add build step' i wybieramy 'Execute sql files'
2. W 'SVN Credentials' wpisujemy dane dostępowe do repozytorium SVN
3. W 'Database Connection' podajemy dane dostępowe do bazy dla Hibernate'a, np:
  Dialect: org.hibernate.dialect.PostgreSQLDialect
  Driver Class: org.postgresql.Driver
  Url: jdbc:postgresql://localhost:5432/dbname
  Username: postgres
  Password: pass
