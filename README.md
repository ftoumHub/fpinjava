# Création du projet parent

```bash
mvn archetype:generate -DgroupId=com.fpinjava -DartifactId=fpinjava-parent
```
Une fois le pom parent généré, modifier le fichier pom.xml pour changer le type de packaging en 'pom'.
<packaging>pom</packaging>

# Ajout d'un nouveau module:

```bash
cd fpinjava-parent
mvn archetype:generate -DgroupId=com.fpinjava -DartifactId=nom_du_module
```