FROM selenium/standalone-chrome:latest
USER root
# install pip
RUN apt-get update && apt-get install -y python3-pip
# install python packages
WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY *.py .
COPY sample.html .
CMD ["python3", "main.py"]
