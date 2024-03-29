import qdrant_client

from python.knowledge.knowledge_base_mock import knowledge_base
from python.knowledge.webscrapper import fetch_and_parse
from llama_index.core import SimpleDirectoryReader

q_client = qdrant_client.QdrantClient("http://localhost:6333", prefer_grpc=True)

ALL = 'all'
KNOWLEDGE_BASE = 'knowledge_base'
WEBPAGE_FIX = 'webpage_fix'
WEBPAGE_SEMANTIC = 'webpage_semantic'

demo_url = 'https://luciangruia.ro/blog/ill-be-speaking-at-voxxed-days-romania-on-march-29th-2024/'


def add_to_collection(collection_name, documents):
    try:
        q_client.add(collection_name=collection_name, documents=documents)
    except Exception as e:
        print(f"Error adding documents to collection '{collection_name}': {e}")


def insert_qdrant(source_name=ALL):
    sources = {
        KNOWLEDGE_BASE: knowledge_base,
        WEBPAGE_FIX: fetch_and_parse(demo_url),
        WEBPAGE_SEMANTIC: fetch_and_parse(demo_url),
    }

    for collection, documents in sources.items():
        if source_name == collection or source_name == ALL:
            add_to_collection(collection, documents)


def query_qdrant(user_prompt):
    all_results = []
    try:
        results = q_client.query(
            collection_name=WEBPAGE_FIX,
            query_text=user_prompt,
            limit=3
        )
        all_results.extend(results)
    except Exception as e:
        print(f"Error querying collection : {e}")
    all_results.sort(key=lambda x: x.score, reverse=True)
    return all_results[0] if all_results else None
