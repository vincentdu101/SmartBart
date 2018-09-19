import { ConfigService } from "../Config/ConfigService";

export namespace StationService {

    export function getStationsInfo(): Promise<any> {
        return fetch(ConfigService.staticStationsInfo).then(results => {
            return results.json();
        });
    }

    export function getStationsEstimates(orig: string): Promise<any> {
        return fetch(ConfigService.staticStationEstimates + "?orig=" + orig).then(results => {
            return results.json();
        });
    }

    export function getStationsEstimatesFiltered(props: Readonly<any>) : Promise<any> {
        let params = "?orig=" + props.origin + "&dest=" + props.destination;
        return fetch(ConfigService.staticFilteredEstimates + params).then(results => {
            return results.json();
        })   
    }

}