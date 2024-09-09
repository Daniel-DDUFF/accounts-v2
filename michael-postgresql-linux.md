# 1. Installer PostgreSQL

Commencez par mettre à jour la liste des paquets :

```bash
sudo apt update
```

Ensuite, installez PostgreSQL :

```bash
sudo apt install postgresql postgresql-contrib
```

Une fois installé, le service PostgreSQL devrait démarrer automatiquement. Vérifiez son état avec :

```bash
sudo systemctl status postgresql
```

# 2. Se connecter à PostgreSQL

Le compte `postgres` est créé automatiquement lors de l'installation. Connectez-vous à PostgreSQL en tant qu'utilisateur `postgres` :

```bash
sudo -i -u postgres
```

Lancez le shell `psql` :

```bash
psql
```

# 3. Créer la base de données `microdemoDb`

Dans le shell `psql`, créez la base de données avec la commande suivante :

```sql
CREATE DATABASE microdemoDb;
```

# 4. Créer un utilisateur

Pour créer un utilisateur, exécutez la commande suivante dans le shell `psql` :

```sql
CREATE USER microdemoUser WITH ENCRYPTED PASSWORD 'yourpassword';
```

Remplacez `yourpassword` par un mot de passe sécurisé.

# 5. Accorder des privilèges à l'utilisateur

Associez l'utilisateur `microdemoUser` à la base de données `microdemoDb` en lui accordant des privilèges :

```sql
GRANT ALL PRIVILEGES ON DATABASE microdemoDb TO microdemoUser;
```

# 6. Quitter le shell `psql`

Pour quitter le shell PostgreSQL, tapez :

```bash
\q
```

# 7. Vérification

Vous pouvez vérifier que tout fonctionne en vous reconnectant avec l'utilisateur nouvellement créé :

```bash
psql -U microdemoUser -d microdemoDb
```

Cela vous demandera le mot de passe, et si tout fonctionne, vous serez connecté à la base de données `microdemoDb` en tant qu'utilisateur `microdemoUser`.


