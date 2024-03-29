from datetime import datetime

from flask import Flask, Blueprint, request, jsonify

from knowledge.qdrant import query_qdrant
from llm.openai import get_llm_response

app = Flask(__name__)
blueprint_conv = Blueprint('blueprint_conv', __name__)

ROLE = "role"
CONTENT = "content"

system_instruct = 'Please provide a response to the user prompt. Be always polite and helpful.'


@blueprint_conv.route('/query', methods=['POST'])
def prompt():
    data = request.get_json()
    user_prompt = data.get('prompt')

    metaprompt = build_meta_prompt(user_prompt)
    print(metaprompt)

    llm_question = [
        {ROLE: "user", CONTENT: metaprompt},
        {ROLE: "system", CONTENT: system_instruct}]
    completion = get_llm_response(llm_question).choices[0].message.content.strip()

    return jsonify({"completion": completion,
                    'message_time': datetime.now()
                    })


def build_meta_prompt(user_prompt):
    return f"user prompt: {user_prompt}\ncontext: {query_qdrant(user_prompt)}"
