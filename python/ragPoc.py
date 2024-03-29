import os

from flask import Flask, send_from_directory
from flask_cors import CORS  # Import CORS

from python.rest.conv import blueprint_conv
from python.rest.init.knowledge_init import blueprint_ki

# Flask
app = Flask(__name__, static_folder='static')
CORS(app)  # Enable CORS on the app

# register REST endpoints
app.register_blueprint(blueprint_ki)
app.register_blueprint(blueprint_conv)


@app.route('/', defaults={'path': ''})
@app.route('/<path:path>')
def serve(path):
    if path != "" and os.path.exists(app.static_folder + '/' + path):
        return send_from_directory(app.static_folder, path)
    else:
        return send_from_directory(app.static_folder, 'index.html')


if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=5500)
