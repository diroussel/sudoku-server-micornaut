FROM oracle/graalvm-ce:20.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/sudoku-server
WORKDIR /home/app/sudoku-server

RUN native-image --no-server -cp build/libs/sudoku-server-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/sudoku-server/sudoku-server /app/sudoku-server
ENTRYPOINT ["/app/sudoku-server"]
