from flask import Flask
from flask_cors import CORS  # Import CORS

from rest.conv import blueprint_conv
from rest.init.knowledge_init import blueprint_ki

# Flask
app = Flask(__name__, static_folder='static')
CORS(app)  # Enable CORS on the app

# register REST endpoints
app.register_blueprint(blueprint_ki)
app.register_blueprint(blueprint_conv)

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=5500)
