FROM node:alpine AS builder
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:alpine

COPY --from=builder /app/dist/ /usr/share/nginx/html/
# Expose the public http port
EXPOSE 80
# Start server
CMD ["nginx", "-g", "daemon off;"]
