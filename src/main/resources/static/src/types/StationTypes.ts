export interface IStationSectionProps {

}

export interface IStationSectionState {
    stations: IStationInfo[]
    maps: any,
    tooltipStation: IStationInfo | undefined,
    tooltipX: number,
    tooltipY: number,
    tooltipActive: boolean,
    tooltipTextCallback: Function
}

export interface IStation {
    id: number;
    description: string;
    createdAt: string;
    modifiedAt: string;
    etds: IEtd[];
    name: string;
    abbr: string;
}

export interface IStationInfo {
    abbr: string;
    address: string;
    city: string;
    county: string;
    gtfsLatitude: number;
    gtfsLongitude: number;
    name: string;
    state: string;
    zipcode: string;
}

export interface IEtd {
    destination: string;
    abbreviation: string;
    limited: number;
    estimates: IEstimate[];
}

export interface IEstimate {
    minutes: number;
    platform: number;
    direction: string;
    length: number;
    bikeflag: boolean;
    delay: number;
}

export interface IStationsTableProps {
    stations: IStationInfo[];
}

export interface IStationsTableState {
    stations: IStationInfo[];
}