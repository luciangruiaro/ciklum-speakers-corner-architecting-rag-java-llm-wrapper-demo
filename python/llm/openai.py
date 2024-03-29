import os

from openai import OpenAI

client = OpenAI(
    api_key=os.environ['OPENAI_API_KEY'],  # this is also the default, it can be omitted
)

os.environ["TOKENIZERS_PARALLELISM"] = "false"


def get_llm_response(curr_messages):
    return client.chat.completions.create(
        model="gpt-3.5-turbo-0125",
        temperature=0.4,
        # max_tokens=150,
        messages=curr_messages
    )
