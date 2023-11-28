# Use an official Node runtime as a parent image
FROM node:20-alpine

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install app dependencies
RUN npm install

# Copy the entire project to the working directory
COPY . .

# Expose the port the app runs on
EXPOSE 8079

# Define the command to run your app
CMD ["npm", "run" , "dev"]
