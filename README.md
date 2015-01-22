# sql-executor-plugin

Przed zbudowaniem projektu powinniśmy mieć skonfigurowane repozytorium jenkinsa w .m2/settings.xml:

<pluginGroups>
    <pluginGroup>org.jenkins-ci.tools</pluginGroup>
</pluginGroups>

<profiles>...
<profile>
      <id>jenkins</id>
      <activation>
        <activeByDefault>true</activeByDefault> <!-- change this to false, if you don't like to have it on per default -->
      </activation>
      <repositories>
        <repository>
          <id>repo.jenkins-ci.org</id>
          <url>http://repo.jenkins-ci.org/public/</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>repo.jenkins-ci.org</id>
          <url>http://repo.jenkins-ci.org/public/</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
...</profiles>

<mirrors>
    <mirror>
      <id>repo.jenkins-ci.org</id>
      <url>http://repo.jenkins-ci.org/public/</url>
      <mirrorOf>m.g.o-public</mirrorOf>
    </mirror>
</mirrors>

Instalacja pluginu:

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
