FROM eclipse-temurin:17

WORKDIR /app

COPY . .

RUN chmod +x gradlew

# キャッシュ問題防止 + 詳細ログ表示
RUN ./gradlew build --no-daemon

# jar名固定で確実起動
CMD ["java", "-jar", "build/libs/LoginFirebaseSpringboot-0.0.1-SNAPSHOT.jar"]
