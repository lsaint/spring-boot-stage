from flask import Flask
app = Flask(__name__)

@app.route("/hello")
def hello():
    return "reply from python flask"

@app.route("/health")
def health():
    return ('', 204)

if __name__ == '__main__':
    app.run()


"""
curl -X PUT --data @register.json http://127.0.0.1:8500/v1/agent/service/register

curl -X PUT http://127.0.0.1:8500/v1/agent/service/deregister/service-id
"""
