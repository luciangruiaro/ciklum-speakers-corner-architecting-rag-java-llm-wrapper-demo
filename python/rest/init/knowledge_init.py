from flask import Flask, Blueprint, request

from python.knowledge.qdrant import insert_qdrant

app = Flask(__name__)
blueprint_ki = Blueprint('blueprint_ki', __name__)

SOURCE_NAME = 'source_name'


@blueprint_ki.route('/knowledge_init', methods=['POST'])
def ki():
    data = request.json
    source_name = data.get(SOURCE_NAME, None)
    print('Start initializing knowledge base.')
    insert_qdrant(source_name)
    print('Knowledge base initialized successfully.')
    return "Success", 200, {"Access-Control-Allow-Origin": "*"}
