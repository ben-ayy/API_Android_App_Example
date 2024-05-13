from flask import Flask

app = Flask(__name__)


@app.route('/', methods=['GET'])
def make_request():
    return "Ainsi bas la vide"


if __name__ == '__main__':
    # app.run(ssl_context=('cert.pem', 'key.pem'))
    app.run(host='0.0.0.0', debug=True)  # Make sure to add --host(0.0.0.0) to run configurations in pycharm
